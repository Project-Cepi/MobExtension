package world.cepi.mob.commands.subcommands

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import world.cepi.kstom.command.kommand.Kommand
import world.cepi.mob.mob.EntityEggData
import world.cepi.mob.mob.mobEgg
import world.cepi.mob.util.MobTextComponents.mobPropertiesToComponent

internal object InfoSubcommand : Kommand({

    syntax().onlyPlayers {
        val mob = player.mobEgg ?: return@onlyPlayers

        player.sendMessage(
            mobPropertiesToComponent("Meta", "Unknown Meta", "Meta", mob.metaMap.values)
                .append(Component.newline())
                .append(mobPropertiesToComponent("Goals", "Unknown Goal", "Goal", mob.goals))
                .append(Component.newline())
                .append(mobPropertiesToComponent("Targets", "Unknown Target", "Target", mob.targets))
                .append(Component.newline())
                .append(Component.text("Type: ", NamedTextColor.GRAY)
                    .append(Component.text(EntityEggData.findByType(mob.type)?.displayName ?: "Unknown", NamedTextColor.WHITE)))
        )

    }


}, "info")