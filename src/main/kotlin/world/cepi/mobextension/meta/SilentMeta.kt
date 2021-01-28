package world.cepi.mobextension.meta

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import net.minestom.server.entity.Entity

@Serializable
@SerialName("silent")
data class SilentMeta(@SerialName("value") val isSilent: Boolean) : MobMeta {
    override fun apply(entity: Entity) {
        entity.isSilent = isSilent
    }
}