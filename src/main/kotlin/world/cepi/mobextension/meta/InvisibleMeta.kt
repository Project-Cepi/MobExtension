package world.cepi.mobextension.meta

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import net.minestom.server.entity.Entity

@Serializable
@SerialName("invisible")
data class InvisibleMeta(@SerialName("value") val isInvisible: Boolean) : MobMeta {
    override fun apply(entity: Entity) {
        entity.isInvisible = isInvisible
    }

    override fun value() = isInvisible.toString()
}