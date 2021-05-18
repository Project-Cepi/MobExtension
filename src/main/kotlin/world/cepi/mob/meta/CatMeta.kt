package world.cepi.mob.meta

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import net.minestom.server.entity.Entity
import net.minestom.server.entity.metadata.animal.tameable.CatMeta

@Serializable
@SerialName("cat-color")
data class CatColorMeta(@SerialName("value") val color: CatMeta.Color) : MobMeta() {
    override fun apply(entity: Entity) {
        (entity.entityMeta as? CatMeta ?: return).color = color
    }
}