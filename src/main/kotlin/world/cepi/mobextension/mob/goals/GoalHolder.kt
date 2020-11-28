package world.cepi.mobextension.mob.goals

import net.minestom.server.entity.ai.GoalSelector

interface GoalHolder {

    val goals: MutableList<GoalSelector>

    fun addGoal(goal: GoalSelector) = goals.add(goal)

}