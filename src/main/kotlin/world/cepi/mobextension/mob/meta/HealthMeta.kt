package world.cepi.mobextension.mob.meta

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import net.minestom.server.entity.Entity
import net.minestom.server.entity.LivingEntity

@Serializable
@SerialName("health")
class HealthMeta(@SerialName("value") val health: Float) : MobMeta {
    override fun apply(entity: Entity) {
        if (entity is LivingEntity) {
            entity.health = health
        }
    }

}