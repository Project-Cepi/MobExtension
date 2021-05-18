package world.cepi.mob.meta

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import net.minestom.server.entity.Entity
import net.minestom.server.entity.metadata.animal.SheepMeta

@Serializable
@SerialName("sheep-sheared")
data class SheepShearedMeta(@SerialName("value") val sheared: Boolean) : MobMeta() {
    override fun apply(entity: Entity) {
        (entity.entityMeta as? SheepMeta ?: return).isSheared = sheared
    }
}