package world.cepi.mob.meta

import kotlin.Boolean
import kotlin.Unit
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import net.minestom.server.coordinate.Vec
import net.minestom.server.entity.Entity
import net.minestom.server.entity.metadata.MobMeta

@Serializable
public object ArmorStandMeta {
  @Serializable
  @SerialName("ArmorStandMeta_setMarker")
  public data class Marker(
    public val arg0: Boolean
  ) : MobMeta() {
    public override fun apply(entity: Entity): Unit {
      (entity as? net.minestom.server.entity.metadata.other.ArmorStandMeta ?:
          return).setMarker(arg0)
    }
  }

  @Serializable
  @SerialName("ArmorStandMeta_setSmall")
  public data class Small(
    public val arg0: Boolean
  ) : MobMeta() {
    public override fun apply(entity: Entity): Unit {
      (entity as? net.minestom.server.entity.metadata.other.ArmorStandMeta ?: return).setSmall(arg0)
    }
  }

  @Serializable
  @SerialName("ArmorStandMeta_setHasArms")
  public data class HasArms(
    public val arg0: Boolean
  ) : MobMeta() {
    public override fun apply(entity: Entity): Unit {
      (entity as? net.minestom.server.entity.metadata.other.ArmorStandMeta ?:
          return).setHasArms(arg0)
    }
  }

  @Serializable
  @SerialName("ArmorStandMeta_setHasNoBasePlate")
  public data class HasNoBasePlate(
    public val arg0: Boolean
  ) : MobMeta() {
    public override fun apply(entity: Entity): Unit {
      (entity as? net.minestom.server.entity.metadata.other.ArmorStandMeta ?:
          return).setHasNoBasePlate(arg0)
    }
  }

  @Serializable
  @SerialName("ArmorStandMeta_setHeadRotation")
  public data class HeadRotation(
    public val arg0: Vec
  ) : MobMeta() {
    public override fun apply(entity: Entity): Unit {
      (entity as? net.minestom.server.entity.metadata.other.ArmorStandMeta ?:
          return).setHeadRotation(arg0)
    }
  }

  @Serializable
  @SerialName("ArmorStandMeta_setBodyRotation")
  public data class BodyRotation(
    public val arg0: Vec
  ) : MobMeta() {
    public override fun apply(entity: Entity): Unit {
      (entity as? net.minestom.server.entity.metadata.other.ArmorStandMeta ?:
          return).setBodyRotation(arg0)
    }
  }

  @Serializable
  @SerialName("ArmorStandMeta_setLeftArmRotation")
  public data class LeftArmRotation(
    public val arg0: Vec
  ) : MobMeta() {
    public override fun apply(entity: Entity): Unit {
      (entity as? net.minestom.server.entity.metadata.other.ArmorStandMeta ?:
          return).setLeftArmRotation(arg0)
    }
  }

  @Serializable
  @SerialName("ArmorStandMeta_setRightArmRotation")
  public data class RightArmRotation(
    public val arg0: Vec
  ) : MobMeta() {
    public override fun apply(entity: Entity): Unit {
      (entity as? net.minestom.server.entity.metadata.other.ArmorStandMeta ?:
          return).setRightArmRotation(arg0)
    }
  }

  @Serializable
  @SerialName("ArmorStandMeta_setLeftLegRotation")
  public data class LeftLegRotation(
    public val arg0: Vec
  ) : MobMeta() {
    public override fun apply(entity: Entity): Unit {
      (entity as? net.minestom.server.entity.metadata.other.ArmorStandMeta ?:
          return).setLeftLegRotation(arg0)
    }
  }

  @Serializable
  @SerialName("ArmorStandMeta_setRightLegRotation")
  public data class RightLegRotation(
    public val arg0: Vec
  ) : MobMeta() {
    public override fun apply(entity: Entity): Unit {
      (entity as? net.minestom.server.entity.metadata.other.ArmorStandMeta ?:
          return).setRightLegRotation(arg0)
    }
  }
}
