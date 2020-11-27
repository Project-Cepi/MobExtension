package world.cepi.mobextension.api

import net.minestom.server.entity.EntityType
import world.cepi.mobextension.api.goals.Goal

abstract class Mob(properties: Properties) {
    class Properties {
        val goals = mutableListOf<Goal>()
        lateinit var type: EntityType

        fun addGoal(goal: Goal): Properties {
            goals.add(goal)
            return this
        }

        fun setType(typeToSet: EntityType): Properties {
            type = typeToSet
            return this
        }
    }

    init {
        val goals = properties.goals
        var meta = MobMeta()
    }


}