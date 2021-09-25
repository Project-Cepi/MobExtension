package world.cepi.mob.commands.subcommands.edit

import net.minestom.server.command.builder.arguments.ArgumentType
import world.cepi.kepi.command.subcommand.applyHelp
import world.cepi.kepi.messages.sendFormattedTranslatableMessage
import world.cepi.kstom.command.kommand.Kommand
import world.cepi.mob.mob.EntityEggData
import world.cepi.mob.mob.mobEgg
import world.cepi.mob.util.MobUtils

internal object TypeSubcommand : Kommand({
    val type = ArgumentType.EntityType("type")

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

    syntax(type).onlyPlayers {
        if (!MobUtils.hasMobEgg(sender)) return@onlyPlayers

        val mob = player.mobEgg ?: return@onlyPlayers

        if (EntityEggData.values().none { context[type] == it.type }) {
            player.sendFormattedTranslatableMessage("common", "error.internal")
            return@onlyPlayers
        }

        mob.type = context[type]

        player.itemInMainHand = mob.generateEgg(player.itemInMainHand)
    }
}, "type")