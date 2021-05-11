package world.cepi.mobextension.meta

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import net.minestom.server.entity.Entity
import net.minestom.server.entity.metadata.AgeableMobMeta

@Serializable
@SerialName("baby")
data class BabyMeta(@SerialName("value") val isBaby: Boolean) : MobMeta() {
    override fun apply(entity: Entity) {
        (entity.entityMeta as? AgeableMobMeta ?: return).isBaby = true
    }
}