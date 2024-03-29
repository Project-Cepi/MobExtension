package world.cepi.mob.meta

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import net.minestom.server.entity.Entity
import net.minestom.server.entity.metadata.other.ItemFrameMeta
import net.minestom.server.item.ItemStack
import world.cepi.kstom.serializer.ItemStackSerializer

@Serializable
public object MetaItemFrame {
  @Serializable
  @SerialName("MetaItemFrame_setItem")
  public data class Item(
    @Serializable(ItemStackSerializer::class)
    public val arg0: ItemStack
  ) : MobMeta() {
    public override fun apply(entity: Entity): Unit {
      (entity.entityMeta as? ItemFrameMeta ?: return).setItem(arg0)
    }
  }

  @Serializable
  @SerialName("MetaItemFrame_setRotation")
  public data class Rotation(
    public val arg0: net.minestom.server.utils.Rotation
  ) : MobMeta() {
    public override fun apply(entity: Entity): Unit {
      (entity.entityMeta as? ItemFrameMeta ?: return).setRotation(arg0)
    }
  }

  @Serializable
  @SerialName("MetaItemFrame_setOrientation")
  public data class Orientation(
    public val arg0: ItemFrameMeta.Orientation
  ) : MobMeta() {
    public override fun apply(entity: Entity): Unit {
      (entity.entityMeta as? ItemFrameMeta ?: return).setOrientation(arg0)
    }
  }
}
