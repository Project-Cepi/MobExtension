package world.cepi.mob.meta

import kotlin.Unit
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import net.minestom.server.entity.Entity
import net.minestom.server.entity.metadata.MobMeta
import net.minestom.server.item.ItemStack

public object ItemFrameMeta {
  @Serializable
  @SerialName("ItemFrameMeta_setOrientation")
  public data class Orientation(
    arg0: net.minestom.server.entity.metadata.other.ItemFrameMeta.Orientation
  ) : MobMeta() {
    public override fun apply(entity: Entity): Unit {
      (entity as? net.minestom.server.entity.metadata.other.ItemFrameMeta ?:
          return).setOrientation(arg0)
    }
  }

  @Serializable
  @SerialName("ItemFrameMeta_setRotation")
  public data class Rotation(
    arg0: net.minestom.server.utils.Rotation
  ) : MobMeta() {
    public override fun apply(entity: Entity): Unit {
      (entity as? net.minestom.server.entity.metadata.other.ItemFrameMeta ?:
          return).setRotation(arg0)
    }
  }

  @Serializable
  @SerialName("ItemFrameMeta_setItem")
  public data class Item(
    arg0: ItemStack
  ) : MobMeta() {
    public override fun apply(entity: Entity): Unit {
      (entity as? net.minestom.server.entity.metadata.other.ItemFrameMeta ?: return).setItem(arg0)
    }
  }
}
