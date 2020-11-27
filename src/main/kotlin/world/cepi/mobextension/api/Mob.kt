package world.cepi.mobextension.api

import world.cepi.mobextension.api.goals.Goal

abstract class Mob(properties: Properties) {
    class Properties {
        internal val goals = mutableListOf<Goal>()

        fun addGoal(goal: Goal): Properties {
            goals.add(goal)
            return this
        }
    }

    init {
        val goals = properties.goals
    }


}