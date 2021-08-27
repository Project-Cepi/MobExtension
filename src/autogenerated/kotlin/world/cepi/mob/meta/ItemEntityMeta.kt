package world.cepi.mob.meta

import kotlin.Unit
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import net.minestom.server.entity.Entity
import net.minestom.server.entity.metadata.MobMeta
import net.minestom.server.item.ItemStack

public object ItemEntityMeta {
  @Serializable
  @SerialName("ItemEntityMeta_setItem")
  public data class Item(
    arg0: ItemStack
  ) : MobMeta() {
    public override fun apply(entity: Entity): Unit {
      (entity as? net.minestom.server.entity.metadata.item.ItemEntityMeta ?: return).setItem(arg0)
    }
  }
}
