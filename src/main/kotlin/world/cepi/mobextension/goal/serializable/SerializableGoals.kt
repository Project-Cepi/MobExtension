package world.cepi.mobextension.goal.serializable

import kotlinx.serialization.Serializable
import net.minestom.server.entity.EntityCreature
import net.minestom.server.entity.ai.GoalSelector
import net.minestom.server.entity.ai.goal.DoNothingGoal
import net.minestom.server.entity.ai.goal.RandomStrollGoal

@Serializable
class DoNothingGoal(private val time: Long, private val chance: Float) : SerialiazableGoal {
    override fun toGoalSelector(creature: EntityCreature): GoalSelector = DoNothingGoal(creature, time, chance)
}

@Serializable
class SerializableRandomStrollGoal(private val radius: Int) : SerialiazableGoal {
    override fun toGoalSelector(creature: EntityCreature): GoalSelector = RandomStrollGoal(creature, radius)
}