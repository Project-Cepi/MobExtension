package world.cepi.mob.commands.subcommands.edit

import net.minestom.server.command.builder.Command
import net.minestom.server.command.builder.arguments.ArgumentType
import net.minestom.server.entity.Player
import world.cepi.kepi.command.subcommand.applyHelp
import world.cepi.kepi.messages.sendFormattedTranslatableMessage
import world.cepi.kstom.command.addSyntax
import world.cepi.mob.mob.EntityEggData
import world.cepi.mob.mob.mobEgg
import world.cepi.mob.util.MobUtils

internal object TypeSubcommand : Command("type") {

    val type = ArgumentType.EntityType("type")

    init {

        applyHelp {
            """
            The type subcommand allows
            you to choose a mob's type.
            
            Feel free to cycle through
            all the types, including
            
            <blue>arrow, cow, ender crystal
            and more!
            """.trimIndent()
        }

        addSyntax(type) {
            if (!MobUtils.hasMobEgg(sender)) return@addSyntax

            val player = sender as Player

            val mob = player.mobEgg ?: return@addSyntax

            if (EntityEggData.values().none { context[type] == it.type }) {
                player.sendFormattedTranslatableMessage("common", "error.internal")
                return@addSyntax
            }

            mob.type = context[type]

            player.itemInMainHand = mob.generateEgg(player.itemInMainHand)
        }

    }

}