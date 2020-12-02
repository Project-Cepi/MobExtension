package world.cepi.mobextension.goal.serializable

import kotlinx.serialization.Serializable
import net.minestom.server.entity.EntityCreature
import net.minestom.server.entity.ai.GoalSelector
import net.minestom.server.entity.ai.goal.DoNothingGoal

@Serializable
class DoNothingGoal(private val time: Long, private val chance: Float) : SerialiazableGoal {
    override fun toGoalSelector(creature: EntityCreature): GoalSelector = DoNothingGoal(creature, time, chance)
}