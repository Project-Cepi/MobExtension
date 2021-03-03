package world.cepi.mobextension.goal

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import net.minestom.server.entity.EntityCreature
import net.minestom.server.entity.EntityType
import net.minestom.server.entity.ai.GoalSelector
import net.minestom.server.entity.ai.goal.*
import net.minestom.server.entity.type.projectile.EntityProjectile
import net.minestom.server.utils.time.TimeUnit
import net.minestom.server.utils.time.UpdateOption

/**
 * A collection of serializable implementations of [GoalSelector]s
 * While it is not enforced by the compiler, as a matter of convention classes in this object must be serializable, implement [SerializableGoal] and have a [SerialName]
 */
object SerializableGoals {
    @SerialName("do_nothing")
    @Serializable
    data class DoNothingGoal(private val time: Long, private val chance: Float) : SerializableGoal {
        override fun toGoalSelector(creature: EntityCreature): GoalSelector = DoNothingGoal(creature, time, chance)
    }

    @SerialName("random_stroll")
    @Serializable
    data class RandomStrollGoal(private val radius: Int) : SerializableGoal {
        override fun toGoalSelector(creature: EntityCreature): GoalSelector = RandomStrollGoal(creature, radius)
    }

    @SerialName("random_look_around")
    @Serializable
    data class RandomLookAroundGoal(private val chancePerTick: Int) : SerializableGoal {
        override fun toGoalSelector(creature: EntityCreature): GoalSelector = RandomLookAroundGoal(creature, chancePerTick)
    }

    @SerialName("follow_target")
    @Serializable
    data class FollowTargetGoal(private val length: Long, val unit: TimeUnit) : SerializableGoal {
        override fun toGoalSelector(creature: EntityCreature): GoalSelector = FollowTargetGoal(creature, UpdateOption(length, unit))
    }

    @SerialName("melee_attack_goal")
    @Serializable
    data class MeleeAttackGoal(private val delay: Int, private val range: Int, private val unit: TimeUnit) : SerializableGoal {
        override fun toGoalSelector(creature: EntityCreature): GoalSelector = MeleeAttackGoal(creature, delay, range, unit)
    }

    @SerialName("ranged_attack_goal")
    @Serializable
    data class RangedAttackGoal(val delay: Int, val attackRange: Int, val desirableRange: Int, val comeClose: Boolean, val power: Double, val spread: Double, val timeUnit: TimeUnit): SerializableGoal {
        override fun toGoalSelector(creature: EntityCreature): GoalSelector {
            val goal = RangedAttackGoal(creature, delay, attackRange, desirableRange, comeClose, power, spread, timeUnit)
            goal.setProjectileGenerator { source ->
                val projectile = EntityProjectile(source, EntityType.ARROW)
                projectile.setInstance(source.instance!!, source.position)
                projectile
            }
            return goal
        }
    }
}