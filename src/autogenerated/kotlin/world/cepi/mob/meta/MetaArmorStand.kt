package world.cepi.mob.meta

import kotlin.Boolean
import kotlin.Unit
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import net.minestom.server.entity.Entity
import net.minestom.server.entity.metadata.other.ArmorStandMeta

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
}
