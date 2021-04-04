package world.cepi.mobextension.meta

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import net.kyori.adventure.text.Component
import net.minestom.server.entity.Entity

@Serializable
@SerialName("name")
data class NameMeta(@SerialName("value") val name: String) : MobMeta {
    override fun apply(entity: Entity) {
        entity.customName = Component.text(name)
    }

    override fun value() = name
}