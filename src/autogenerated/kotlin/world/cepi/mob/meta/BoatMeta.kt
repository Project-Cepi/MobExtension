package world.cepi.mob.meta

import kotlin.Boolean
import kotlin.Float
import kotlin.Int
import kotlin.Unit
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import net.minestom.server.entity.Entity
import net.minestom.server.entity.metadata.MobMeta

@Serializable
public object BoatMeta {
  @Serializable
  @SerialName("BoatMeta_setType")
  public data class Type(
    public val arg0: net.minestom.server.entity.metadata.other.BoatMeta.Type
  ) : MobMeta() {
    public override fun apply(entity: Entity): Unit {
      (entity as? net.minestom.server.entity.metadata.other.BoatMeta ?: return).setType(arg0)
    }
  }

  @Serializable
  @SerialName("BoatMeta_setTimeSinceLastHit")
  public data class TimeSinceLastHit(
    public val arg0: Int
  ) : MobMeta() {
    public override fun apply(entity: Entity): Unit {
      (entity as? net.minestom.server.entity.metadata.other.BoatMeta ?:
          return).setTimeSinceLastHit(arg0)
    }
  }

  @Serializable
  @SerialName("BoatMeta_setForwardDirection")
  public data class ForwardDirection(
    public val arg0: Int
  ) : MobMeta() {
    public override fun apply(entity: Entity): Unit {
      (entity as? net.minestom.server.entity.metadata.other.BoatMeta ?:
          return).setForwardDirection(arg0)
    }
  }

  @Serializable
  @SerialName("BoatMeta_setDamageTaken")
  public data class DamageTaken(
    public val arg0: Float
  ) : MobMeta() {
    public override fun apply(entity: Entity): Unit {
      (entity as? net.minestom.server.entity.metadata.other.BoatMeta ?: return).setDamageTaken(arg0)
    }
  }

  @Serializable
  @SerialName("BoatMeta_setLeftPaddleTurning")
  public data class LeftPaddleTurning(
    public val arg0: Boolean
  ) : MobMeta() {
    public override fun apply(entity: Entity): Unit {
      (entity as? net.minestom.server.entity.metadata.other.BoatMeta ?:
          return).setLeftPaddleTurning(arg0)
    }
  }

  @Serializable
  @SerialName("BoatMeta_setRightPaddleTurning")
  public data class RightPaddleTurning(
    public val arg0: Boolean
  ) : MobMeta() {
    public override fun apply(entity: Entity): Unit {
      (entity as? net.minestom.server.entity.metadata.other.BoatMeta ?:
          return).setRightPaddleTurning(arg0)
    }
  }

  @Serializable
  @SerialName("BoatMeta_setSplashTimer")
  public data class SplashTimer(
    public val arg0: Int
  ) : MobMeta() {
    public override fun apply(entity: Entity): Unit {
      (entity as? net.minestom.server.entity.metadata.other.BoatMeta ?: return).setSplashTimer(arg0)
    }
  }
}
