package world.cepi.mob.meta

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import net.minestom.server.entity.Entity
import world.cepi.kstom.command.arguments.generation.annotations.DefaultBoolean

@Serializable
@SerialName("invisible")
data class InvisibleMeta(
    @param:DefaultBoolean(true)
    @SerialName("value") val isInvisible: Boolean
) : MobMeta() {
    override fun apply(entity: Entity) {
        entity.isInvisible = isInvisible
    }
}