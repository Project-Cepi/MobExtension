package world.cepi.mob.meta

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import net.minestom.server.entity.Entity
import net.minestom.server.entity.metadata.AgeableMobMeta
import world.cepi.kstom.command.arguments.annotations.DefaultBoolean

@Serializable
@SerialName("baby")
data class BabyMeta(
    @param:DefaultBoolean(true)
    @SerialName("value") val isBaby: Boolean
) : MobMeta() {
    override fun apply(entity: Entity) {
        (entity.entityMeta as? AgeableMobMeta ?: return).isBaby = true
    }
}