package world.cepi.mob.meta.generated

import kotlin.Boolean
import kotlin.Int
import kotlin.Unit
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import net.minestom.server.entity.Entity
import net.minestom.server.entity.metadata.animal.StriderMeta
import world.cepi.mob.meta.MobMeta

@Serializable
public object MetaStrider {
  @Serializable
  @SerialName("MetaStrider_setHasSaddle")
  public data class HasSaddle(
    public val arg0: Boolean
  ) : MobMeta() {
    public override fun apply(entity: Entity): Unit {
      (entity.entityMeta as? StriderMeta ?: return).setHasSaddle(arg0)
    }
  }

  @Serializable
  @SerialName("MetaStrider_setTimeToBoost")
  public data class TimeToBoost(
    public val arg0: Int
  ) : MobMeta() {
    public override fun apply(entity: Entity): Unit {
      (entity.entityMeta as? StriderMeta ?: return).setTimeToBoost(arg0)
    }
  }

  @Serializable
  @SerialName("MetaStrider_setShaking")
  public data class Shaking(
    public val arg0: Boolean
  ) : MobMeta() {
    public override fun apply(entity: Entity): Unit {
      (entity.entityMeta as? StriderMeta ?: return).setShaking(arg0)
    }
  }
}
