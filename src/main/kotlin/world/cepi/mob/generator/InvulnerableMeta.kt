package world.cepi.mob.generator

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import net.minestom.server.entity.Entity
import net.minestom.server.tag.Tag
import world.cepi.kstom.command.arguments.generation.annotations.DefaultBoolean

@Serializable
@SerialName("invulnerable")
data class InvulnerableMeta(
    @param:DefaultBoolean(true)
    @SerialName("value") val isInvulnerable: Boolean
) : SealedMobMeta() {
    override fun apply(entity: Entity) {
        entity.setTag(Tag.Byte(tagName), 1)
    }

    companion object {
        const val tagName = "invulnerable"
    }
}