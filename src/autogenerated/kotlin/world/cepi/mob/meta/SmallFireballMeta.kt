package world.cepi.mob.meta

import kotlin.Unit
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import net.minestom.server.entity.Entity
import net.minestom.server.entity.metadata.MobMeta
import net.minestom.server.item.ItemStack

@Serializable
public object SmallFireballMeta {
  @Serializable
  @SerialName("SmallFireballMeta_setShooter")
  public data class Shooter(
    public val arg0: Entity
  ) : MobMeta() {
    public override fun apply(entity: Entity): Unit {
      (entity as? net.minestom.server.entity.metadata.item.SmallFireballMeta ?:
          return).setShooter(arg0)
    }
  }

  @Serializable
  @SerialName("SmallFireballMeta_setItem")
  public data class Item(
    public val arg0: ItemStack
  ) : MobMeta() {
    public override fun apply(entity: Entity): Unit {
      (entity as? net.minestom.server.entity.metadata.item.SmallFireballMeta ?:
          return).setItem(arg0)
    }
  }
}
