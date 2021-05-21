package world.cepi.mob.commands.subcommands.edit

import net.minestom.server.command.builder.Command
import net.minestom.server.command.builder.arguments.ArgumentType
import net.minestom.server.entity.Player
import world.cepi.kstom.command.addSyntax
import world.cepi.mob.commands.MobCommand
import world.cepi.mob.mob.EntityData
import world.cepi.mob.mob.mobEgg

internal object TypeSubcommand : Command("type") {

    init {

        val type = ArgumentType.Word("type")
            .from(*EntityData.values().map { it.type.name.lowercase() }.toTypedArray())

        addSyntax(type) { sender, args ->
            if (!MobCommand.hasMobEgg(sender)) return@addSyntax

            val player = sender as Player

            val mob = player.mobEgg ?: return@addSyntax

            mob.type = EntityData.values().firstOrNull { it.type.name.equals(args.get(type), ignoreCase = true) }!!.type

            player.itemInMainHand = mob.generateEgg()
        }

    }

}