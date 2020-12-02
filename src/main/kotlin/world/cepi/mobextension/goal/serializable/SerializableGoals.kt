package world.cepi.mobextension.goal.serializable

import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import net.minestom.server.entity.EntityCreature
import net.minestom.server.entity.ai.GoalSelector
import net.minestom.server.entity.ai.goal.DoNothingGoal
import net.minestom.server.entity.ai.goal.FollowTargetGoal
import net.minestom.server.entity.ai.goal.MeleeAttackGoal
import net.minestom.server.entity.ai.goal.RandomStrollGoal
import net.minestom.server.utils.time.TimeUnit
import net.minestom.server.utils.time.UpdateOption

object SerializableGoals {
    @Serializable
    class DoNothingGoal(private val time: Long, private val chance: Float) : SerialiazableGoal {
        override fun toGoalSelector(creature: EntityCreature): GoalSelector = DoNothingGoal(creature, time, chance)
    }

    @Serializable
    class RandomStrollGoal(private val radius: Int) : SerialiazableGoal {
        override fun toGoalSelector(creature: EntityCreature): GoalSelector = RandomStrollGoal(creature, radius)
    }

    @Serializable
    class FollowTargetGoal(@Contextual private val pathUpdateOption: UpdateOption) : SerialiazableGoal {
        override fun toGoalSelector(creature: EntityCreature): GoalSelector = FollowTargetGoal(creature, pathUpdateOption)
    }

    @Serializable
    class MeleeAttackGoal(private val delay: Int, private val unit: TimeUnit) : SerialiazableGoal {
        override fun toGoalSelector(creature: EntityCreature): GoalSelector = MeleeAttackGoal(creature, delay, unit)
    }
}