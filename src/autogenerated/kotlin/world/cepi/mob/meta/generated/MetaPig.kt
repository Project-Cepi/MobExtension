package world.cepi.mob.meta.generated

import kotlin.Boolean
import kotlin.Int
import kotlin.Unit
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import net.minestom.server.entity.Entity
import net.minestom.server.entity.metadata.animal.PigMeta
import world.cepi.mob.meta.MobMeta

@Serializable
public object MetaPig {
  @Serializable
  @SerialName("MetaPig_setHasSaddle")
  public data class HasSaddle(
    public val arg0: Boolean
  ) : MobMeta() {
    public override fun apply(entity: Entity): Unit {
      (entity.entityMeta as? PigMeta ?: return).setHasSaddle(arg0)
    }
  }

  @Serializable
  @SerialName("MetaPig_setTimeToBoost")
  public data class TimeToBoost(
    public val arg0: Int
  ) : MobMeta() {
    public override fun apply(entity: Entity): Unit {
      (entity.entityMeta as? PigMeta ?: return).setTimeToBoost(arg0)
    }
  }
}
