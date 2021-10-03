package world.cepi.mob.property

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import net.minestom.server.entity.EntityCreature
import world.cepi.kstom.adventure.asMini

@Serializable
@SerialName("name")
data class NameProperty(@SerialName("value") val name: String) : MobProperty() {
    override fun apply(entityCreature: EntityCreature) {
        entityCreature.customName = name.asMini()
    }

}