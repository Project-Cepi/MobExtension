package world.cepi.mob.meta

import kotlin.Byte
import kotlin.Unit
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import net.minestom.server.coordinate.Point
import net.minestom.server.entity.Entity
import net.minestom.server.entity.metadata.MobMeta
import net.minestom.server.utils.Direction

public object ShulkerMeta {
  @Serializable
  @SerialName("ShulkerMeta_setColor")
  public data class Color(
    arg0: Byte
  ) : MobMeta() {
    public override fun apply(entity: Entity): Unit {
      (entity as? net.minestom.server.entity.metadata.golem.ShulkerMeta ?: return).setColor(arg0)
    }
  }

  @Serializable
  @SerialName("ShulkerMeta_setAttachFace")
  public data class AttachFace(
    arg0: Direction
  ) : MobMeta() {
    public override fun apply(entity: Entity): Unit {
      (entity as? net.minestom.server.entity.metadata.golem.ShulkerMeta ?:
          return).setAttachFace(arg0)
    }
  }

  @Serializable
  @SerialName("ShulkerMeta_setAttachmentPosition")
  public data class AttachmentPosition(
    arg0: Point
  ) : MobMeta() {
    public override fun apply(entity: Entity): Unit {
      (entity as? net.minestom.server.entity.metadata.golem.ShulkerMeta ?:
          return).setAttachmentPosition(arg0)
    }
  }

  @Serializable
  @SerialName("ShulkerMeta_setShieldHeight")
  public data class ShieldHeight(
    arg0: Byte
  ) : MobMeta() {
    public override fun apply(entity: Entity): Unit {
      (entity as? net.minestom.server.entity.metadata.golem.ShulkerMeta ?:
          return).setShieldHeight(arg0)
    }
  }
}
