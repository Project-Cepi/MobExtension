package world.cepi.mobextension.goal.serializable

import kotlinx.serialization.Contextual
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import net.minestom.server.entity.EntityCreature
import net.minestom.server.entity.ai.GoalSelector
import net.minestom.server.entity.ai.goal.*
import net.minestom.server.entity.ai.goal.RandomLookAroundGoal
import net.minestom.server.utils.time.TimeUnit
import net.minestom.server.utils.time.UpdateOption

object SerializableGoals {
    @SerialName("do_nothing")
    @Serializable
    class DoNothingGoal(private val time: Long, private val chance: Float) : SerializableGoal {
        override fun toGoalSelector(creature: EntityCreature): GoalSelector = DoNothingGoal(creature, time, chance)
    }

    @SerialName("random_stroll")
    @Serializable
    class RandomStrollGoal(private val radius: Int) : SerializableGoal {
        override fun toGoalSelector(creature: EntityCreature): GoalSelector = RandomStrollGoal(creature, radius)
    }

    @SerialName("random_look_around")
    @Serializable
    class RandomLookAroundGoal(private val chancePerTick: Int) : SerializableGoal {
        override fun toGoalSelector(creature: EntityCreature): GoalSelector = RandomLookAroundGoal(creature, chancePerTick)
    }

    @SerialName("follow_target")
    @Serializable
    class FollowTargetGoal(@Contextual private val pathUpdateOption: UpdateOption) : SerializableGoal {
        override fun toGoalSelector(creature: EntityCreature): GoalSelector = FollowTargetGoal(creature, pathUpdateOption)
    }

    @SerialName("melee_attack_goal")
    @Serializable
    class MeleeAttackGoal(private val delay: Int, private val unit: TimeUnit) : SerializableGoal {
        override fun toGoalSelector(creature: EntityCreature): GoalSelector = MeleeAttackGoal(creature, delay, unit)
    }
}