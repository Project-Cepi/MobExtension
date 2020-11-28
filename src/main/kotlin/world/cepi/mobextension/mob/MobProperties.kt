package world.cepi.mobextension.mob

import net.minestom.server.entity.EntityType
import net.minestom.server.entity.ai.GoalSelector
import world.cepi.mobextension.mob.conditional.Conditional
import world.cepi.mobextension.mob.conditional.ConditionalBuilder
import world.cepi.mobextension.mob.meta.MobMeta

class MobProperties {

    val conditions: MutableList<Conditional> = mutableListOf()
    val goals: MutableList<GoalSelector> = mutableListOf()
    val metas: MutableList<MobMeta<*>> = mutableListOf()

    fun addMeta(meta: MobMeta<*>) = metas.add(meta)
    fun addGoal(goal: GoalSelector) = goals.add(goal)
    fun addConditional(conditional: Conditional) = conditions.add(conditional)
    fun addConditional(conditionalBuilder: ConditionalBuilder) = conditions.add(conditionalBuilder.build())

    lateinit var type: EntityType

    fun setType(typeToSet: EntityType): MobProperties {
        type = typeToSet
        return this
    }
}