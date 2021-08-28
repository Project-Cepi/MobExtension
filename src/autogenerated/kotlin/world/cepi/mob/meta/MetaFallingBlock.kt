package world.cepi.mob.meta

import kotlin.Unit
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import net.minestom.server.coordinate.Point
import net.minestom.server.entity.Entity
import net.minestom.server.entity.metadata.other.FallingBlockMeta

@Serializable
public object MetaFallingBlock {
  @Serializable
  @SerialName("MetaFallingBlock_setSpawnPosition")
  public data class SpawnPosition(
    public val arg0: Point
  ) : MobMeta() {
    public override fun apply(entity: Entity): Unit {
      (entity as? FallingBlockMeta ?: return).setSpawnPosition(arg0)
    }
  }

  @Serializable
  @SerialName("MetaFallingBlock_setBlock")
  public data class Block(
    public val arg0: net.minestom.server.instance.block.Block
  ) : MobMeta() {
    public override fun apply(entity: Entity): Unit {
      (entity as? FallingBlockMeta ?: return).setBlock(arg0)
    }
  }
}
