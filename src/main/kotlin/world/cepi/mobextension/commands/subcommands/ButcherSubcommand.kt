package world.cepi.mobextension.commands.subcommands

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import net.minestom.server.command.builder.Command
import net.minestom.server.command.builder.arguments.ArgumentType
import net.minestom.server.entity.Player
import net.minestom.server.utils.entity.EntityFinder
import world.cepi.kepi.messages.sendFormattedTranslatableMessage
import world.cepi.kstom.command.addSyntax
import java.util.function.Supplier

object ButcherSubcommand : Command("butcher") {

    init {
        val radius = ArgumentType.Integer("radius").min(1).max(100)
        val finder = ArgumentType.Entity("entities")

        finder.defaultValue = Supplier {
            EntityFinder().setTargetSelector(EntityFinder.TargetSelector.ALL_ENTITIES)
        }

        addSyntax(radius, finder) { sender, args ->
            val player = sender as? Player ?: return@addSyntax

            val foundEntities = args.get(finder).find(player)
                .filter { it !is Player } // no players
                .filter { it.getDistance(player) <= args.get(radius) } // at that distance

            foundEntities.forEach { it.remove() }

            player.sendFormattedTranslatableMessage(
                "mob", if (foundEntities.size == 1) "butcher.single" else "butcher.plural",
                Component.text(foundEntities.size, NamedTextColor.BLUE),
            )
        }
    }

}