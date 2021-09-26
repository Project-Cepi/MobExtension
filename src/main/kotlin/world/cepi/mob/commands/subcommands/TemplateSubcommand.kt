package world.cepi.mob.commands.subcommands

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import net.minestom.server.utils.time.TimeUnit
import world.cepi.kepi.messages.sendFormattedTranslatableMessage
import world.cepi.kstom.command.arguments.literal
import world.cepi.kstom.command.kommand.Kommand
import world.cepi.mob.goal.SerializableGoals
import world.cepi.mob.mob.mob
import world.cepi.mob.targets.SerializableTargets
import java.time.Duration

internal object TemplateSubcommand : Kommand({

    onlyPlayers

    val map = mapOf(
        "meleeAttacker" to
                mob {
                    goal(SerializableGoals.ContactMeleeAttackGoal(Duration.of(10, TimeUnit.SERVER_TICK)))
                    target(SerializableTargets.ClosestPlayerTarget(20f))
                }
    )

    map.forEach { key, value ->
        syntax(key.literal()) {
            player.itemInMainHand = value.generateEgg(player.itemInMainHand)

            player.sendFormattedTranslatableMessage("mob", "template",
                Component.text(key, NamedTextColor.BLUE)
            )
        }
    }

}, "template")