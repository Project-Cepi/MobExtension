package world.cepi.mob.targets

import net.minestom.server.entity.Entity
import net.minestom.server.entity.EntityCreature
import net.minestom.server.entity.ai.TargetSelector
import net.minestom.server.utils.entity.EntityFinder
import world.cepi.mob.util.MobUtils

class SelectorTarget(entityCreature: EntityCreature, val finder: EntityFinder) : TargetSelector(entityCreature) {
    override fun findTarget(): Entity? {

        val instance = getEntityCreature().instance!!

        val entities = finder.find(instance, entityCreature).filter {
            MobUtils.isValidTarget(it, entityCreature)
        }

        return if (entities.isEmpty()) null
        else entities[0]
    }


}