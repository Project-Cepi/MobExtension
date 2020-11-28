package world.cepi.mobextension.mob

import net.minestom.server.entity.EntityType
import net.minestom.server.entity.ai.GoalSelector
import world.cepi.mobextension.mob.conditional.Conditional
import world.cepi.mobextension.mob.meta.MobMeta

class MobProperties {

    val conditions: MutableList<Conditional> = mutableListOf()
    val goals: MutableList<GoalSelector> = mutableListOf()
    val metas: MutableList<MobMeta<*>> = mutableListOf()

    fun addMeta(meta: MobMeta<*>): MobProperties {
        metas.add(meta)
        return this
    }

    fun addGoal(goal: GoalSelector): MobProperties {
        goals.add(goal)
        return this
    }

    fun addConditional(conditional: Conditional): MobProperties {
        conditions.add(conditional)
        return this
    }

    lateinit var type: EntityType

    fun setType(typeToSet: EntityType): MobProperties {
        type = typeToSet
        return this
    }
}