package world.cepi.mobextension.commands.subcommands

import net.kyori.adventure.text.Component
import net.minestom.server.command.builder.Command
import net.minestom.server.entity.Player
import world.cepi.kepi.messages.sendFormattedMessage
import world.cepi.kstom.command.addSyntax
import world.cepi.kstom.command.arguments.argumentsFromConstructor
import world.cepi.kstom.command.arguments.asSubcommand
import world.cepi.mobextension.Mob
import world.cepi.mobextension.StaticObjectCollection
import world.cepi.mobextension.commands.MobCommand
import kotlin.reflect.KClass
import kotlin.reflect.full.primaryConstructor

internal open class GenericMobListSubcommand(
    name: String,
    collection: StaticObjectCollection<KClass<*>>,
    addToMob: (Mob, Any) -> Unit,
    addedMessage: String
) : Command(name) {

    init {

        val add = "add".asSubcommand()

        collection.objects.forEach { clazz ->

            val arguments = argumentsFromConstructor(clazz.primaryConstructor!!)

            var clazzArgumentName = clazz.simpleName!!.toLowerCase()
            clazzArgumentName = clazzArgumentName.dropLast(name.length)

            addSyntax(add, clazzArgumentName.asSubcommand(), *arguments.toTypedArray()) { sender, args ->
                if (!MobCommand.hasMobEgg(sender)) return@addSyntax

                val player = sender as Player

                val mob = player.itemInMainHand.data?.get<Mob>(Mob.mobKey)!!

                val objectArg = clazz.primaryConstructor!!.call(*arguments.map { args.get(it) }.toTypedArray())

                addToMob(mob, objectArg)

                player.itemInMainHand = mob.generateEgg()

                player.sendFormattedMessage(Component.text(addedMessage), Component.text(clazzArgumentName))
            }

        }
    }

}