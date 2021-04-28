package world.cepi.mobextension.commands.subcommands

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import net.minestom.server.command.builder.Command
import net.minestom.server.entity.Player
import world.cepi.kstom.command.addSyntax
import world.cepi.mobextension.EntityData
import world.cepi.mobextension.mob
import world.cepi.mobextension.util.MobTextComponents.mobPropertiesToComponent

internal object InfoSubcommand : Command("info") {

    init {
        addSyntax { sender ->
            val player = sender as Player

            val mob = player.mob ?: return@addSyntax

            player.sendMessage(
                mobPropertiesToComponent("Meta", "Unknown Meta", "Meta", mob.properties.metas.values)
                    .append(Component.newline())
                    .append(mobPropertiesToComponent("Goals", "Unknown Goal", "Goal", mob.properties.goals))
                    .append(Component.newline())
                    .append(mobPropertiesToComponent("Targets", "Unknown Target", "Target", mob.properties.targets))
                    .append(Component.newline())
                    .append(Component.text("Type: ", NamedTextColor.GRAY)
                        .append(Component.text(EntityData.findByType(mob.properties.type)?.displayName ?: "Unknown", NamedTextColor.WHITE)))
            )

        }
    }

}