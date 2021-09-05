package world.cepi.mob.meta

import kotlin.Boolean
import kotlin.Unit
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import net.minestom.server.coordinate.Vec
import net.minestom.server.entity.Entity
import net.minestom.server.entity.metadata.other.ArmorStandMeta
import world.cepi.kstom.serializer.VectorSerializer

@Serializable
public object MetaArmorStand {
  @Serializable
  @SerialName("MetaArmorStand_setMarker")
  public data class Marker(
    public val arg0: Boolean
  ) : MobMeta() {
    public override fun apply(entity: Entity): Unit {
      (entity.entityMeta as? ArmorStandMeta ?: return).setMarker(arg0)
    }
  }

  @Serializable
  @SerialName("MetaArmorStand_setSmall")
  public data class Small(
    public val arg0: Boolean
  ) : MobMeta() {
    public override fun apply(entity: Entity): Unit {
      (entity.entityMeta as? ArmorStandMeta ?: return).setSmall(arg0)
    }
  }

  @Serializable
  @SerialName("MetaArmorStand_setHasArms")
  public data class HasArms(
    public val arg0: Boolean
  ) : MobMeta() {
    public override fun apply(entity: Entity): Unit {
      (entity.entityMeta as? ArmorStandMeta ?: return).setHasArms(arg0)
    }
  }

  @Serializable
  @SerialName("MetaArmorStand_setHasNoBasePlate")
  public data class HasNoBasePlate(
    public val arg0: Boolean
  ) : MobMeta() {
    public override fun apply(entity: Entity): Unit {
      (entity.entityMeta as? ArmorStandMeta ?: return).setHasNoBasePlate(arg0)
    }
  }

  @Serializable
  @SerialName("MetaArmorStand_setHeadRotation")
  public data class HeadRotation(
    @Serializable(VectorSerializer::class)
    public val arg0: Vec
  ) : MobMeta() {
    public override fun apply(entity: Entity): Unit {
      (entity.entityMeta as? ArmorStandMeta ?: return).setHeadRotation(arg0)
    }
  }

  @Serializable
  @SerialName("MetaArmorStand_setBodyRotation")
  public data class BodyRotation(
    @Serializable(VectorSerializer::class)
    public val arg0: Vec
  ) : MobMeta() {
    public override fun apply(entity: Entity): Unit {
      (entity.entityMeta as? ArmorStandMeta ?: return).setBodyRotation(arg0)
    }
  }

  @Serializable
  @SerialName("MetaArmorStand_setLeftArmRotation")
  public data class LeftArmRotation(
    @Serializable(VectorSerializer::class)
    public val arg0: Vec
  ) : MobMeta() {
    public override fun apply(entity: Entity): Unit {
      (entity.entityMeta as? ArmorStandMeta ?: return).setLeftArmRotation(arg0)
    }
  }

  @Serializable
  @SerialName("MetaArmorStand_setRightArmRotation")
  public data class RightArmRotation(
    @Serializable(VectorSerializer::class)
    public val arg0: Vec
  ) : MobMeta() {
    public override fun apply(entity: Entity): Unit {
      (entity.entityMeta as? ArmorStandMeta ?: return).setRightArmRotation(arg0)
    }
  }

  @Serializable
  @SerialName("MetaArmorStand_setLeftLegRotation")
  public data class LeftLegRotation(
    @Serializable(VectorSerializer::class)
    public val arg0: Vec
  ) : MobMeta() {
    public override fun apply(entity: Entity): Unit {
      (entity.entityMeta as? ArmorStandMeta ?: return).setLeftLegRotation(arg0)
    }
  }

  @Serializable
  @SerialName("MetaArmorStand_setRightLegRotation")
  public data class RightLegRotation(
    @Serializable(VectorSerializer::class)
    public val arg0: Vec
  ) : MobMeta() {
    public override fun apply(entity: Entity): Unit {
      (entity.entityMeta as? ArmorStandMeta ?: return).setRightLegRotation(arg0)
    }
  }
}
