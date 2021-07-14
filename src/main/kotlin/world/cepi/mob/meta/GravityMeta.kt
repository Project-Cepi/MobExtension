package world.cepi.mob.meta

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import net.minestom.server.entity.Entity
import world.cepi.kstom.command.arguments.generation.annotations.DefaultBoolean

@Serializable
@SerialName("gravity")
data class GravityMeta(
    @param:DefaultBoolean(true)
    @SerialName("value") val hasGravity: Boolean
) : MobMeta() {
    override fun apply(entity: Entity) {
        entity.setNoGravity(hasGravity)
    }
}