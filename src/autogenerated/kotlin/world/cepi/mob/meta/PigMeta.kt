package world.cepi.mob.meta

import kotlin.Boolean
import kotlin.Int
import kotlin.Unit
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import net.minestom.server.entity.Entity
import net.minestom.server.entity.metadata.MobMeta

public object PigMeta {
  @Serializable
  @SerialName("PigMeta_setHasSaddle")
  public data class HasSaddle(
    arg0: Boolean
  ) : MobMeta() {
    public override fun apply(entity: Entity): Unit {
      (entity as? net.minestom.server.entity.metadata.animal.PigMeta ?: return).setHasSaddle(arg0)
    }
  }

  @Serializable
  @SerialName("PigMeta_setTimeToBoost")
  public data class TimeToBoost(
    arg0: Int
  ) : MobMeta() {
    public override fun apply(entity: Entity): Unit {
      (entity as? net.minestom.server.entity.metadata.animal.PigMeta ?: return).setTimeToBoost(arg0)
    }
  }
}
