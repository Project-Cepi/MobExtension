package world.cepi.mobextension.mob.meta

import net.minestom.server.entity.Entity
import net.minestom.server.entity.LivingEntity

class HealthMeta(val health: Float) : MobMeta {
    override fun apply(entity: Entity) {
        if (entity is LivingEntity) {
            entity.health = health
        }
    }

}