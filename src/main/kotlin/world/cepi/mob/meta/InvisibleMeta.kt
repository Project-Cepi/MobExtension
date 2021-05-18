package world.cepi.mob.meta

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import net.minestom.server.entity.Entity

@Serializable
@SerialName("invisible")
data class InvisibleMeta(@SerialName("value") val isInvisible: Boolean) : MobMeta() {
    override fun apply(entity: Entity) {
        entity.isInvisible = isInvisible
    }
}