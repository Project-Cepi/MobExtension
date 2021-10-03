package world.cepi.mob.property

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import net.minestom.server.entity.EntityCreature
import net.minestom.server.tag.Tag

@Serializable
@SerialName("invulnerable")
class InvulnerableProperty : MobProperty() {
    override fun apply(entityCreature: EntityCreature) {
        entityCreature.setTag(Tag.Byte(tagName), 1)
    }

    companion object {
        const val tagName = "invulnerable"
    }
}