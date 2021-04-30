package world.cepi.mobextension.goal

import kotlinx.serialization.Serializable
import net.minestom.server.entity.EntityCreature
import net.minestom.server.entity.ai.GoalSelector

/** A goal that can be serialized by kotlinx. */
@Serializable
sealed class SerializableGoal {
    /**
     *  @param creature The EntityCreature that the Goal will be associated with.
     *  @return the [GoalSelector] that this SerializableGoal represents
     */
    abstract fun toGoalSelector(creature: EntityCreature): GoalSelector
}