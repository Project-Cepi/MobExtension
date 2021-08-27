package world.cepi.mob.meta

import kotlin.Unit
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import net.minestom.server.entity.Entity
import net.minestom.server.entity.metadata.MobMeta
import net.minestom.server.item.ItemStack

@Serializable
public object FireballMeta {
  @Serializable
  @SerialName("FireballMeta_setShooter")
  public data class Shooter(
    public val arg0: Entity
  ) : MobMeta() {
    public override fun apply(entity: Entity): Unit {
      (entity as? net.minestom.server.entity.metadata.item.FireballMeta ?: return).setShooter(arg0)
    }
  }

  @Serializable
  @SerialName("FireballMeta_setItem")
  public data class Item(
    public val arg0: ItemStack
  ) : MobMeta() {
    public override fun apply(entity: Entity): Unit {
      (entity as? net.minestom.server.entity.metadata.item.FireballMeta ?: return).setItem(arg0)
    }
  }
}
