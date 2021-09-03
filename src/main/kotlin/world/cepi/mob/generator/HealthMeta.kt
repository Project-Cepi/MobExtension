package world.cepi.mob.generator

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import net.minestom.server.attribute.Attribute
import net.minestom.server.entity.Entity
import net.minestom.server.entity.LivingEntity

@Serializable
@SerialName("health")
data class HealthMeta(@SerialName("value") val health: Float) : SealedMobMeta() {
    override fun apply(entity: Entity) {
        if (entity is LivingEntity) {
            entity.getAttribute(Attribute.MAX_HEALTH).baseValue = health
            entity.health = health
        }
    }

}