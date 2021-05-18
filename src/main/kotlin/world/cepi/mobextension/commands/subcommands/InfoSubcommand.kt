package world.cepi.mobextension.commands.subcommands

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import net.minestom.server.command.builder.Command
import net.minestom.server.entity.Player
import world.cepi.kstom.command.addSyntax
import world.cepi.mobextension.mob.EntityData
import world.cepi.mobextension.mob.mobEgg
import world.cepi.mobextension.util.MobTextComponents.mobPropertiesToComponent

internal object InfoSubcommand : Command("info") {

    init {
        addSyntax { sender ->
            val player = sender as Player

            val mob = player.mobEgg ?: return@addSyntax

            player.sendMessage(
                mobPropertiesToComponent("Meta", "Unknown Meta", "Meta", mob.metas.values)
                    .append(Component.newline())
                    .append(mobPropertiesToComponent("Goals", "Unknown Goal", "Goal", mob.goals))
                    .append(Component.newline())
                    .append(mobPropertiesToComponent("Targets", "Unknown Target", "Target", mob.targets))
                    .append(Component.newline())
                    .append(Component.text("Type: ", NamedTextColor.GRAY)
                        .append(Component.text(EntityData.findByType(mob.type)?.displayName ?: "Unknown", NamedTextColor.WHITE)))
            )

        }
    }

}