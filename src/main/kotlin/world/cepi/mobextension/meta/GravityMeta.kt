package world.cepi.mobextension.meta

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import net.minestom.server.entity.Entity

@Serializable
@SerialName("gravity")
data class GravityMeta(@SerialName("value") val hasGravity: Boolean) : MobMeta {
    override fun apply(entity: Entity) {
        entity.setNoGravity(hasGravity)
    }

    override fun value() = hasGravity.toString()
}