package world.cepi.mobextension.commands.subcommands

import net.minestom.server.command.builder.Command
import net.minestom.server.command.builder.arguments.ArgumentType
import net.minestom.server.entity.Player
import world.cepi.kstom.command.addSyntax

object ButcherSubcommand : Command("butcher") {

    init {
        val radius = ArgumentType.Integer("radius").min(1).max(100)

        addSyntax(radius) { sender, args ->
            val player = sender as? Player ?: return@addSyntax

            val foundEntities = player.instance!!.entities
                .filter { it !is Player } // no players
                .filter { it.getDistance(player) <= args.get(radius) } // at that distance

            foundEntities.forEach { it.remove() }

            // TODO message!
        }
    }

}