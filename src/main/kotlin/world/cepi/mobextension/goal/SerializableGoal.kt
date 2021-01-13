package world.cepi.mobextension.goal

import net.minestom.server.entity.EntityCreature
import net.minestom.server.entity.ai.GoalSelector

/** A goal that can be serialized by kotlinx. */
interface SerializableGoal {
    /**
     *  @param creature The EntityCreature that the Goal will be associated with.
     *  @return the [GoalSelector] that this SerializableGoal represents
     */
    fun toGoalSelector(creature: EntityCreature): GoalSelector
}