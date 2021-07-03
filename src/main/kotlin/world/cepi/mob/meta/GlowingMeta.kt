package world.cepi.mob.meta

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import net.minestom.server.entity.Entity
import world.cepi.kstom.command.arguments.annotations.DefaultBoolean

@Serializable
@SerialName("glowing")
data class GlowingMeta(
    @param:DefaultBoolean(true)
    @SerialName("value") val isGlowing: Boolean
) : MobMeta() {
    override fun apply(entity: Entity) {
        entity.isGlowing = isGlowing
    }
}