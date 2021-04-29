package world.cepi.mobextension.commands.subcommands

import net.kyori.adventure.text.Component
import net.minestom.server.command.CommandSender
import net.minestom.server.command.builder.Command
import net.minestom.server.entity.Player
import world.cepi.kepi.messages.sendFormattedMessage
import world.cepi.kstom.command.addSyntax
import world.cepi.kstom.command.arguments.argumentsFromConstructor
import world.cepi.kstom.command.arguments.asSubcommand
import world.cepi.mobextension.Mob
import world.cepi.mobextension.StaticObjectCollection
import world.cepi.mobextension.commands.MobCommand
import world.cepi.mobextension.mob
import world.cepi.mobextension.util.MobTextComponents.mobPropertiesToComponent
import kotlin.reflect.KClass
import kotlin.reflect.full.primaryConstructor

internal open class GenericMobListSubcommand(
    /** The name of the command */
    name: String,
    /** The collection which to generate from. */
    collection: StaticObjectCollection<KClass<*>>,
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

        val add = "add".asSubcommand()
        val list = "list".asSubcommand()

        addSyntax(list) { sender ->
            val player = sender as? Player ?: return@addSyntax

            val mob = player.mob ?: return@addSyntax

            val items = grabFromMob(mob)

            player.sendMessage(mobPropertiesToComponent(displayName, unknownName, drop, items))
        }

        collection.objects.forEach { clazz ->

            val arguments = argumentsFromConstructor(clazz.primaryConstructor!!)

            var clazzArgumentName = clazz.simpleName!!.lowercase()
            clazzArgumentName = clazzArgumentName.dropLast(name.length)

            addSyntax(add, clazzArgumentName.asSubcommand(), *arguments.toTypedArray()) { sender, args ->
                if (!MobCommand.hasMobEgg(sender)) return@addSyntax

                val player = sender as Player

                val mob = player.mob ?: return@addSyntax

                val objectArg = clazz.primaryConstructor!!.call(*arguments.map { args.get(it) }.toTypedArray())

                addToMob(mob, objectArg)

                player.itemInMainHand = mob.generateEgg()

                player.sendFormattedMessage(addedMessage(player), Component.text(clazzArgumentName))
            }

        }
    }

}