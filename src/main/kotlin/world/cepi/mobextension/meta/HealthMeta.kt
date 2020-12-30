package world.cepi.mobextension.meta

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import net.minestom.server.attribute.Attributes
import net.minestom.server.entity.Entity
import net.minestom.server.entity.LivingEntity

@Serializable
@SerialName("health")
data class HealthMeta(@SerialName("value") val health: Float) : MobMeta {
    override fun apply(entity: Entity) {
        if (entity is LivingEntity)
            entity.getAttribute(Attributes.MAX_HEALTH).baseValue = health
    }

}