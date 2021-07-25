package world.cepi.mob.targets

import net.minestom.server.entity.Entity
import net.minestom.server.entity.EntityCreature
import net.minestom.server.entity.ai.TargetSelector
import net.minestom.server.entity.damage.EntityDamage
import world.cepi.mob.util.MobUtils

/**
 * Targets the last damager of this entity.
 */
class LastEntityDamagerTarget(entityCreature: EntityCreature, private val range: Float) : TargetSelector(entityCreature) {

    var lastDamageSource: Entity? = null

    override fun findTarget(): Entity? {
        val entity = ((entityCreature.lastDamageSource as? EntityDamage)?.source)
            ?: lastDamageSource ?: return null

        if (entity.isRemoved) {
            // Entity not valid
            return null
        }

        // Invalid target
        if (!MobUtils.isValidTarget(entity, entityCreature)) {
            return null
        }

        lastDamageSource = entity

        // Check range
        return if (entityCreature.getDistance(entity) < range) entity else null
    }
}