package world.cepi.mob.meta

import kotlin.Boolean
import kotlin.Unit
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import net.minestom.server.entity.Entity
import net.minestom.server.entity.metadata.MobMeta

public object PolarBearMeta {
  @Serializable
  @SerialName("PolarBearMeta_setStandingUp")
  public data class StandingUp(
    arg0: Boolean
  ) : MobMeta() {
    public override fun apply(entity: Entity): Unit {
      (entity as? net.minestom.server.entity.metadata.animal.PolarBearMeta ?:
          return).setStandingUp(arg0)
    }
  }
}
