package world.cepi.mobextension.mob

import net.minestom.server.entity.EntityType
import net.minestom.server.entity.ai.GoalSelector
import world.cepi.mobextension.mob.conditional.Conditional
import world.cepi.mobextension.mob.conditional.ConditionalHolder
import world.cepi.mobextension.mob.goals.GoalHolder
import world.cepi.mobextension.mob.meta.MetaHolder
import world.cepi.mobextension.mob.meta.MobMeta

class MobProperties : ConditionalHolder, MetaHolder, GoalHolder {
    override val conditions = mutableListOf<Conditional>()
    override val metas = mutableListOf<MobMeta<*>>()
    override val goals = mutableListOf<GoalSelector>()
    lateinit var type: EntityType
    
    fun setType(typeToSet: EntityType): MobProperties {
        type = typeToSet
        return this
    }
}