package world.cepi.mob.meta

import kotlin.Byte
import kotlin.Unit
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import net.minestom.server.coordinate.Point
import net.minestom.server.entity.Entity
import net.minestom.server.entity.metadata.golem.ShulkerMeta
import net.minestom.server.utils.Direction

@Serializable
public object MetaShulker {
  @Serializable
  @SerialName("MetaShulker_setColor")
  public data class Color(
    public val arg0: Byte
  ) : MobMeta() {
    public override fun apply(entity: Entity): Unit {
      (entity as? ShulkerMeta ?: return).setColor(arg0)
    }
  }

  @Serializable
  @SerialName("MetaShulker_setAttachFace")
  public data class AttachFace(
    public val arg0: Direction
  ) : MobMeta() {
    public override fun apply(entity: Entity): Unit {
      (entity as? ShulkerMeta ?: return).setAttachFace(arg0)
    }
  }

  @Serializable
  @SerialName("MetaShulker_setAttachmentPosition")
  public data class AttachmentPosition(
    public val arg0: Point
  ) : MobMeta() {
    public override fun apply(entity: Entity): Unit {
      (entity as? ShulkerMeta ?: return).setAttachmentPosition(arg0)
    }
  }

  @Serializable
  @SerialName("MetaShulker_setShieldHeight")
  public data class ShieldHeight(
    public val arg0: Byte
  ) : MobMeta() {
    public override fun apply(entity: Entity): Unit {
      (entity as? ShulkerMeta ?: return).setShieldHeight(arg0)
    }
  }
}
