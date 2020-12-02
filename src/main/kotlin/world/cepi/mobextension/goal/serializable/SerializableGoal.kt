package world.cepi.mobextension.goal.serializable

import net.minestom.server.entity.EntityCreature
import net.minestom.server.entity.ai.GoalSelector

interface SerializableGoal {
    /**
     *  @param creature The EntityCreature that the Goal will be associated with.
     *  @return the [GoalSelector] that this SerializableGoal represents
     */
    fun toGoalSelector(creature: EntityCreature): GoalSelector
}