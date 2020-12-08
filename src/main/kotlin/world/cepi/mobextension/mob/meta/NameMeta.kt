package world.cepi.mobextension.mob.meta

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import net.minestom.server.chat.ColoredText
import net.minestom.server.entity.Entity

@Serializable
@SerialName("name")
class NameMeta(@SerialName("value") val name: String) : MobMeta {
    override fun apply(entity: Entity) {
        entity.customName = ColoredText.of(name)
    }
}