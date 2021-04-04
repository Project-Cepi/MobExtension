package world.cepi.mobextension.commands.subcommands

import net.kyori.adventure.text.Component
import net.minestom.server.command.builder.Command
import net.minestom.server.entity.Player
import world.cepi.kepi.messages.sendFormattedMessage
import world.cepi.kstom.command.addSyntax
import world.cepi.kstom.command.arguments.argumentsFromConstructor
import world.cepi.kstom.command.arguments.asSubcommand
import world.cepi.mobextension.Mob
import world.cepi.mobextension.commands.MobCommand
import world.cepi.mobextension.commands.mobGoalSet
import world.cepi.mobextension.goal.GoalObjectCollection
import kotlin.reflect.full.primaryConstructor

internal object GoalSubcommand : Command("goal") {

    init {

        val add = "add".asSubcommand()

        GoalObjectCollection.objects.forEach { clazz ->

            val arguments = argumentsFromConstructor(clazz.primaryConstructor!!)

            var clazzArgumentName = clazz.simpleName!!.toLowerCase()
            clazzArgumentName = clazzArgumentName.substring(0, clazzArgumentName.length - 4)

            addSyntax(add, clazzArgumentName.asSubcommand(), *arguments.toTypedArray()) { sender, args ->
                if (!MobCommand.hasMobEgg(sender)) return@addSyntax

                val player = sender as Player

                val mob = player.itemInMainHand.data?.get<Mob>(Mob.mobKey)!!

                val goalArg = clazz.primaryConstructor!!.call(*arguments.map { args.get(it) }.toTypedArray())

                mob.properties.addGoal(goalArg)

                player.itemInMainHand = mob.generateEgg()

                player.sendFormattedMessage(Component.text(mobGoalSet), Component.text(clazzArgumentName))
            }

        }
    }

}