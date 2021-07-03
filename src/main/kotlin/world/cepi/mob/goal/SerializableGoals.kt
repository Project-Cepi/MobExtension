package world.cepi.mob.goal

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import net.minestom.server.entity.EntityCreature
import net.minestom.server.entity.EntityProjectile
import net.minestom.server.entity.EntityType
import net.minestom.server.entity.ai.GoalSelector
import net.minestom.server.entity.ai.goal.*
import net.minestom.server.utils.Vector
import net.minestom.server.utils.time.TimeUnit
import net.minestom.server.utils.time.UpdateOption
import world.cepi.kstom.Manager
import world.cepi.kstom.command.arguments.annotations.*
import world.cepi.kstom.serializer.UpdateOptionSerializer
import world.cepi.kstom.serializer.VectorSerializer

/**
 * A collection of serializable implementations of [GoalSelector]s
 * While it is not enforced by the compiler,
 * as a matter of convention classes in this object must be serializable,
 * implement [SerializableGoal] and have a [SerialName]
 */
object SerializableGoals {
    @SerialName("do_nothing")
    @Serializable
    data class DoNothingGoal(
        val time: Int,
        val chance: Float
    ) : SerializableGoal() {
        override fun toGoalSelector(creature: EntityCreature): GoalSelector =
            DoNothingGoal(creature, time.toLong(), chance)
    }

    @SerialName("random_stroll")
    @Serializable
    data class RandomStrollGoal(
        @param:DefaultNumber(5.0)
        val radius: Int
    ) : SerializableGoal() {
        override fun toGoalSelector(creature: EntityCreature): GoalSelector = RandomStrollGoal(creature, radius)
    }

    @SerialName("random_look_around")
    @Serializable
    data class RandomLookAroundGoal(
        val chancePerTick: Int
    ) : SerializableGoal() {
        override fun toGoalSelector(creature: EntityCreature): GoalSelector =
            RandomLookAroundGoal(creature, chancePerTick)
    }

    @SerialName("follow_target")
    @Serializable
    data class FollowTargetGoal(
        @param:DefaultUpdateOption(5, TimeUnit.TICK)
        val updateOption: @Serializable(with = UpdateOptionSerializer::class) UpdateOption
    ): SerializableGoal() {
        override fun toGoalSelector(creature: EntityCreature): GoalSelector = FollowTargetGoal(creature, updateOption)
    }

    @SerialName("melee_attack_goal")
    @Serializable
    data class MeleeAttackGoal(
        @param:MinAmount(0.0)
        val range: Int,
        @param:DefaultUpdateOption(1, TimeUnit.SECOND)
        val delayUpdateOption: @Serializable(with = UpdateOptionSerializer::class) UpdateOption
    ) : SerializableGoal() {
        override fun toGoalSelector(creature: EntityCreature): GoalSelector =
            MeleeAttackGoal(creature, delayUpdateOption.value.toDouble(), range, delayUpdateOption.timeUnit)
    }

    @SerialName("contact_melee_attack_goal")
    @Serializable
    data class ContactMeleeAttackGoal(
        @param:DefaultUpdateOption(1, TimeUnit.SECOND)
        val delayUpdateOption: @Serializable(with = UpdateOptionSerializer::class) UpdateOption
    ) : SerializableGoal() {
        override fun toGoalSelector(creature: EntityCreature): GoalSelector =
            ContactMeleeAttackGoal(creature, delayUpdateOption)
    }

    @SerialName("go_to_goal")
    @Serializable
    data class GoToGoal(
        val origin: @Serializable(with = VectorSerializer::class) Vector,
        @param:MinAmount(0.1)
        @param:DefaultNumber(1.0)
        val minDistance: Double
    ): SerializableGoal() {
        override fun toGoalSelector(creature: EntityCreature): GoalSelector =
            GoToGoal(creature, origin, minDistance)
    }

    @SerialName("ranged_attack_goal")
    @Serializable
    data class RangedAttackGoal(
        @param:MinAmount(1.0)
        val attackRange: Int,
        @param:MinAmount(1.0)
        val desirableRange: Int,
        @param:DefaultBoolean(true)
        val comeClose: Boolean,
        @param:DefaultNumber(1.0)
        val power: Double,
        @param:MinAmount(0.0)
        @param:MaxAmount(1.0)
        @param:DefaultNumber(.0)
        val spread: Double,
        val delayUpdateOption: @Serializable(with = UpdateOptionSerializer::class) UpdateOption,
        val decayUpdateOption: @Serializable(with = UpdateOptionSerializer::class) UpdateOption
    ): SerializableGoal() {
        override fun toGoalSelector(creature: EntityCreature): GoalSelector {
            val goal = RangedAttackGoal(
                creature,
                delayUpdateOption.value.toInt(),
                attackRange,
                desirableRange,
                comeClose,
                power,
                spread,
                delayUpdateOption.timeUnit
            )

            goal.setProjectileGenerator { source ->
                val projectile = EntityProjectile(source, EntityType.ARROW)
                projectile.setInstance(source.instance!!, source.position)

                Manager.scheduler.buildTask {
                    projectile.remove()
                }.delay(decayUpdateOption.value, decayUpdateOption.timeUnit).schedule()

                projectile
            }
            return goal
        }
    }
}