package world.cepi.mob.meta

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import net.minestom.server.entity.Entity

@Serializable
@SerialName("glowing")
data class GlowingMeta(@SerialName("value") val isGlowing: Boolean) : MobMeta() {
    override fun apply(entity: Entity) {
        entity.isGlowing = isGlowing
    }
}