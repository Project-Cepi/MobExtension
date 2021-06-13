package world.cepi.mob.commands.subcommands.edit

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import net.minestom.server.command.CommandSender
import net.minestom.server.command.builder.Command
import net.minestom.server.command.builder.arguments.ArgumentType
import net.minestom.server.entity.Player
import world.cepi.kepi.command.subcommand.applyHelp
import world.cepi.kepi.messages.sendFormattedMessage
import world.cepi.kepi.messages.sendFormattedTranslatableMessage
import world.cepi.kstom.command.addSyntax
import world.cepi.kstom.command.arguments.argumentsFromClass
import world.cepi.kstom.command.arguments.literal
import world.cepi.mob.commands.MobCommand
import world.cepi.mob.mob.Mob
import world.cepi.mob.mob.mobEgg
import world.cepi.mob.util.MobTextComponents.mobPropertiesToComponent
import kotlin.reflect.KClass
import kotlin.reflect.full.primaryConstructor
import kotlin.reflect.full.valueParameters

internal sealed class GenericMobListSubcommand(
    /** The name of the command */
    name: String,
    /** The sealed class which to generate from. */
    sealedClass: KClass<*>,
    /** Lambda to add the object to the [Mob]. */
    addToMob: (Mob, Any) -> Unit,
    /** Lambda to grab the data from a mob. */
    grabFromMob: (Mob) -> Collection<Any>,
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
) : Command(name) {

    init {

        applyHelp { helpSyntax }

        val add = "add".literal()
        val info = "info".literal()
        val list = "list".literal()
        val remove = "remove".literal()

        val index = ArgumentType.Integer("index").min(0)

        addSyntax(list) { sender ->
            val player = sender as? Player ?: return@addSyntax

            val mob = player.mobEgg ?: return@addSyntax

            val items = grabFromMob(mob)

            player.sendMessage(mobPropertiesToComponent(displayName, unknownName, drop, items))
        }

        addSyntax(remove, index) { sender, args ->
            val player = sender as? Player ?: return@addSyntax

            val mob = player.mobEgg ?: return@addSyntax

            if (args[index] > mob.goals.size - 1) {
                player.sendFormattedTranslatableMessage(
                    "common", "generic.index",
                    Component.text(name, NamedTextColor.BLUE)
                )
            }

            mob.goals.removeAt(args[index])
        }

        sealedClass.sealedSubclasses.forEach { clazz ->

            val arguments = argumentsFromClass(clazz)

            val clazzFormattedName = clazz.simpleName!!.dropLast(name.length)
            val clazzArgumentName = clazzFormattedName.lowercase()

            addSyntax(add, clazzArgumentName.literal(), *arguments.args) { sender, args ->
                if (!MobCommand.hasMobEgg(sender)) return@addSyntax

                val player = sender as Player

                val mob = player.mobEgg ?: return@addSyntax

                val objectArg = arguments.createInstance(args, sender)

                addToMob(mob, objectArg)

                player.itemInMainHand = mob.generateEgg()

                player.sendFormattedMessage(addedMessage(player, Component.text(clazzArgumentName)))
            }

            addSyntax(info, clazzArgumentName.literal()) { sender, _ ->
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
    }

}