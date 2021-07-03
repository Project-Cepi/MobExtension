package world.cepi.mob.meta

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import net.minestom.server.entity.Entity
import net.minestom.server.entity.metadata.other.FallingBlockMeta
import net.minestom.server.instance.block.Block

@Serializable
@SerialName("block")
data class BlockMeta(
    @SerialName("value") val block: Block
) : MobMeta() {
    override fun apply(entity: Entity) {
        (entity.entityMeta as? FallingBlockMeta ?: return).block = block
    }
}