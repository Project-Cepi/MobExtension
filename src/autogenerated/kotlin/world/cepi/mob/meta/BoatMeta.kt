package world.cepi.mob.meta

import kotlin.Boolean
import kotlin.Float
import kotlin.Int
import kotlin.Unit
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import net.minestom.server.entity.Entity
import net.minestom.server.entity.metadata.MobMeta

public object BoatMeta {
  @Serializable
  @SerialName("BoatMeta_setType")
  public data class Type(
    arg0: net.minestom.server.entity.metadata.other.BoatMeta.Type
  ) : MobMeta() {
    public override fun apply(entity: Entity): Unit {
      (entity as? net.minestom.server.entity.metadata.other.BoatMeta ?: return).setType(arg0)
    }
  }

  @Serializable
  @SerialName("BoatMeta_setTimeSinceLastHit")
  public data class TimeSinceLastHit(
    arg0: Int
  ) : MobMeta() {
    public override fun apply(entity: Entity): Unit {
      (entity as? net.minestom.server.entity.metadata.other.BoatMeta ?:
          return).setTimeSinceLastHit(arg0)
    }
  }

  @Serializable
  @SerialName("BoatMeta_setForwardDirection")
  public data class ForwardDirection(
    arg0: Int
  ) : MobMeta() {
    public override fun apply(entity: Entity): Unit {
      (entity as? net.minestom.server.entity.metadata.other.BoatMeta ?:
          return).setForwardDirection(arg0)
    }
  }

  @Serializable
  @SerialName("BoatMeta_setDamageTaken")
  public data class DamageTaken(
    arg0: Float
  ) : MobMeta() {
    public override fun apply(entity: Entity): Unit {
      (entity as? net.minestom.server.entity.metadata.other.BoatMeta ?: return).setDamageTaken(arg0)
    }
  }

  @Serializable
  @SerialName("BoatMeta_setLeftPaddleTurning")
  public data class LeftPaddleTurning(
    arg0: Boolean
  ) : MobMeta() {
    public override fun apply(entity: Entity): Unit {
      (entity as? net.minestom.server.entity.metadata.other.BoatMeta ?:
          return).setLeftPaddleTurning(arg0)
    }
  }

  @Serializable
  @SerialName("BoatMeta_setRightPaddleTurning")
  public data class RightPaddleTurning(
    arg0: Boolean
  ) : MobMeta() {
    public override fun apply(entity: Entity): Unit {
      (entity as? net.minestom.server.entity.metadata.other.BoatMeta ?:
          return).setRightPaddleTurning(arg0)
    }
  }

  @Serializable
  @SerialName("BoatMeta_setSplashTimer")
  public data class SplashTimer(
    arg0: Int
  ) : MobMeta() {
    public override fun apply(entity: Entity): Unit {
      (entity as? net.minestom.server.entity.metadata.other.BoatMeta ?: return).setSplashTimer(arg0)
    }
  }
}
