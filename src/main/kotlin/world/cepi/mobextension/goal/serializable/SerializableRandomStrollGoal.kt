package world.cepi.mobextension.goal.serializable

import kotlinx.serialization.Serializable
import net.minestom.server.entity.EntityCreature
import net.minestom.server.entity.ai.GoalSelector
import net.minestom.server.entity.ai.goal.RandomStrollGoal

@Serializable
class SerializableRandomStrollGoal(private val radius: Int) : SerialiazableGoal {
    override fun toGoal(creature: EntityCreature): GoalSelector = RandomStrollGoal(creature, radius)
}