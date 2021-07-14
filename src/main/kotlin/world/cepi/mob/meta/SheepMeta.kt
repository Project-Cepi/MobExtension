package world.cepi.mob.meta

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import net.minestom.server.entity.Entity
import net.minestom.server.entity.metadata.animal.SheepMeta
import world.cepi.kstom.command.arguments.generation.annotations.DefaultBoolean

@Serializable
@SerialName("sheep-sheared")
data class SheepShearedMeta(
    @param:DefaultBoolean(true)
    @SerialName("value") val sheared: Boolean
) : MobMeta() {
    override fun apply(entity: Entity) {
        (entity.entityMeta as? SheepMeta ?: return).isSheared = sheared
    }
}