package world.cepi.mob.commands.subcommands

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import net.minestom.server.entity.EntityType
import net.minestom.server.utils.time.TimeUnit
import world.cepi.kepi.command.subcommand.applyHelp
import world.cepi.kepi.messages.sendFormattedTranslatableMessage
import world.cepi.kstom.command.arguments.literal
import world.cepi.kstom.command.kommand.Kommand
import world.cepi.mob.goal.SerializableGoals
import world.cepi.mob.mob.mob
import world.cepi.mob.property.HealthProperty
import world.cepi.mob.property.NoGravityProperty
import world.cepi.mob.targets.SerializableTargets
import java.time.Duration

internal object TemplateSubcommand : Kommand({

    onlyPlayers

    val map = mapOf(
        "melee_attacker" to
                mob(EntityType.ZOMBIE) {
                    goal(SerializableGoals.MeleeAttackGoal(1.0, Duration.of(10, TimeUnit.SERVER_TICK)))
                    target(SerializableTargets.ClosestPlayerTarget(20f))
                },
        "projectile" to
                mob(EntityType.BEE) {
                    property(
                        NoGravityProperty(),
                        HealthProperty(1f)
                    )
                }
    )

    map.forEach { (key, value) ->
        syntax(key.literal()) {
            player.itemInMainHand = value.generateEgg(player.itemInMainHand)

            player.sendFormattedTranslatableMessage("mob", "template",
                Component.text(key, NamedTextColor.BLUE)
            )
        }
    }

    applyHelp {
        """
            The templates subcommand gives you access to demo <blue>mobs"
            
            Usage: <yellow>/mob template (templateName)
        """.trimIndent()
    }

}, "template")