package world.cepi.mob.commands.subcommands.edit

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import net.minestom.server.command.CommandSender
import net.minestom.server.command.builder.arguments.ArgumentType
import net.minestom.server.entity.Player
import world.cepi.kepi.command.subcommand.applyHelp
import world.cepi.kepi.messages.sendFormattedMessage
import world.cepi.kepi.messages.sendFormattedTranslatableMessage
import world.cepi.kstom.command.arguments.generation.generateSyntaxes
import world.cepi.kstom.command.arguments.literal
import world.cepi.kstom.command.kommand.Kommand
import world.cepi.mob.mob.Mob
import world.cepi.mob.mob.mobEgg
import world.cepi.mob.util.MobTextComponents.mobPropertiesToComponent
import world.cepi.mob.util.MobUtils
import kotlin.reflect.KClass
import kotlin.reflect.full.primaryConstructor
import kotlin.reflect.full.valueParameters

internal sealed class GenericMobListSubcommand(
    /** The name of the command */
    name: String,
    /** The sealed class which to generate from. */
    sealedClass: KClass<*>,
    /** Lambda to add the object to the [Mob]. */
    addToMob: Mob.(Any) -> Unit,
    /** Lambda to grab the data from a mob. */
    grabFromMob: Mob.() -> MutableList<out Any>,
    /** The display name of this command, always plural and title case. */
    displayName: String,
    /** The string to display if the Data's class name isn't found. Usually "Unknown [name] " */
    unknownName: String,
    /** The string to drop from the class name */
    drop: String,
    /** The message to send if the Data was added successfully. Lambda to support translations */
    addedMessage: (CommandSender, Component) -> Component,
    /** The help subcommand to use for this command */
    helpSyntax: String
) : Kommand({

    applyHelp { helpSyntax }

    val add by literal
    val info by literal
    val list by literal
    val remove by literal
    val clear by literal

    val index = ArgumentType.Integer("index").min(0)

    syntax(list).onlyPlayers {
        val mob = player.mobEgg ?: return@onlyPlayers

        val items = grabFromMob(mob)

        player.sendMessage(mobPropertiesToComponent(displayName, unknownName, drop, items))
    }

    syntax(remove, index).onlyPlayers {
        val mob = player.mobEgg ?: return@onlyPlayers

        if (context[index] >= mob.goals.size) {
            player.sendFormattedTranslatableMessage(
                "common", "generic.index",
                Component.text(name, NamedTextColor.BLUE)
            )

            return@onlyPlayers
        }

        mob.grabFromMob().removeAt(context[index])

        player.itemInMainHand = mob.generateEgg(player.itemInMainHand)
    }

    syntax(clear).onlyPlayers {
        val mob = player.mobEgg ?: return@onlyPlayers
        mob.grabFromMob().clear()

        player.itemInMainHand = mob.generateEgg(player.itemInMainHand)
    }

    sealedClass.sealedSubclasses.forEach { clazz ->

        val syntaxes = generateSyntaxes(clazz)

        val clazzFormattedName = clazz.simpleName!!.dropLast(name.length)
        val clazzArgumentName = clazzFormattedName.lowercase()

        syntaxes.applySyntax(this, add, clazzArgumentName.literal()) { instance ->
            if (!MobUtils.hasMobEgg(sender)) return@applySyntax

            val player = sender as Player

            val mob = player.mobEgg ?: return@applySyntax

            mob.addToMob(instance)

            player.itemInMainHand = mob.generateEgg(player.itemInMainHand)

            player.sendFormattedMessage(addedMessage(player, Component.text(clazzArgumentName)))
        }

        syntax(info, clazzArgumentName.literal()) {
            sender.sendMessage(
                Component.text("$clazzFormattedName:", NamedTextColor.GRAY)
                    .append(Component.newline())
                    .let {
                        it.append(clazz.primaryConstructor!!.valueParameters
                            .map { value -> value.name to value.type.classifier!! as KClass<*> }
                            .map { target ->
                            // Drop the "drop" keyword's length from the target class's name (if the name doesnt exist use unknownProperty)
                            Component.text(target.first ?: unknownName, NamedTextColor.WHITE)
                                // Upcoming (key: type...) component
                                .append(Component.text(" (${
                                    target.second.simpleName
                                })", NamedTextColor.GRAY))
                                .append(Component.newline())
                        }.reduce { acc, textComponent -> acc.append(textComponent) })
                    }
            )
        }

    }


}, name)