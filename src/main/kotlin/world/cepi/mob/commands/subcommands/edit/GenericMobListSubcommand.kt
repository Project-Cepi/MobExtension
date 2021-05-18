package world.cepi.mob.commands.subcommands.edit

import net.kyori.adventure.text.Component
import net.minestom.server.command.CommandSender
import net.minestom.server.command.builder.Command
import net.minestom.server.entity.Player
import world.cepi.kepi.messages.sendFormattedMessage
import world.cepi.kstom.command.addSyntax
import world.cepi.kstom.command.arguments.argumentsFromClass
import world.cepi.kstom.command.arguments.literal
import world.cepi.mob.commands.MobCommand
import world.cepi.mob.mob.Mob
import world.cepi.mob.mob.mobEgg
import world.cepi.mob.util.MobTextComponents.mobPropertiesToComponent
import kotlin.reflect.KClass

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
    addedMessage: (CommandSender) -> Component
) : Command(name) {

    init {

        val add = "add".literal()
        val list = "list".literal()

        addSyntax(list) { sender ->
            val player = sender as? Player ?: return@addSyntax

            val mob = player.mobEgg ?: return@addSyntax

            val items = grabFromMob(mob)

            player.sendMessage(mobPropertiesToComponent(displayName, unknownName, drop, items))
        }

        sealedClass.sealedSubclasses.forEach { clazz ->

            val arguments = argumentsFromClass(clazz)

            var clazzArgumentName = clazz.simpleName!!.lowercase()
            clazzArgumentName = clazzArgumentName.dropLast(name.length)

            addSyntax(add, clazzArgumentName.literal(), *arguments.args) { sender, args ->
                if (!MobCommand.hasMobEgg(sender)) return@addSyntax

                val player = sender as Player

                val mob = player.mobEgg ?: return@addSyntax

                val objectArg = arguments.createInstance(args, sender)

                addToMob(mob, objectArg)

                player.itemInMainHand = mob.generateEgg()

                player.sendFormattedMessage(addedMessage(player), Component.text(clazzArgumentName))
            }

        }
    }

}