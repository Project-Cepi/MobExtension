package world.cepi.mobextension.meta

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import net.minestom.server.entity.Entity

@Serializable
@SerialName("fire")
data class FireMeta(@SerialName("value") val isOnFire: Boolean) : MobMeta() {
    override fun apply(entity: Entity) {
        entity.isOnFire = isOnFire
    }
}