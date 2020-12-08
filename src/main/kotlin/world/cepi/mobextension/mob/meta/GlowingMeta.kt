package world.cepi.mobextension.mob.meta

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import net.minestom.server.entity.Entity

@Serializable
@SerialName("glowing")
class GlowingMeta(@SerialName("value") val isGlowing: Boolean) : MobMeta {
    override fun apply(entity: Entity) {
        entity.isGlowing = isGlowing
    }
}