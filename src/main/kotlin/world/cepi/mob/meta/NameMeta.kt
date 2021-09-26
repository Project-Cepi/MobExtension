package world.cepi.mob.meta

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import net.minestom.server.entity.Entity
import world.cepi.kstom.adventure.asMini

@Serializable
@SerialName("name")
data class NameMeta(@SerialName("value") val name: String) : MobMeta() {
    override fun apply(entity: Entity) {
        entity.customName = name.asMini()
    }

}