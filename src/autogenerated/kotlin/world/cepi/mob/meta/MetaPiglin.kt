package world.cepi.mob.meta

import kotlin.Boolean
import kotlin.Unit
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import net.minestom.server.entity.Entity
import net.minestom.server.entity.metadata.monster.PiglinMeta

@Serializable
public object MetaPiglin {
  @Serializable
  @SerialName("MetaPiglin_setBaby")
  public data class Baby(
    public val arg0: Boolean
  ) : MobMeta() {
    public override fun apply(entity: Entity): Unit {
      (entity.entityMeta as? PiglinMeta ?: return).setBaby(arg0)
    }
  }

  @Serializable
  @SerialName("MetaPiglin_setChargingCrossbow")
  public data class ChargingCrossbow(
    public val arg0: Boolean
  ) : MobMeta() {
    public override fun apply(entity: Entity): Unit {
      (entity.entityMeta as? PiglinMeta ?: return).setChargingCrossbow(arg0)
    }
  }

  @Serializable
  @SerialName("MetaPiglin_setDancing")
  public data class Dancing(
    public val arg0: Boolean
  ) : MobMeta() {
    public override fun apply(entity: Entity): Unit {
      (entity.entityMeta as? PiglinMeta ?: return).setDancing(arg0)
    }
  }
}
