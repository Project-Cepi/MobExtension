package world.cepi.mob.meta

import kotlin.Boolean
import kotlin.Unit
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import net.minestom.server.entity.Entity
import net.minestom.server.entity.metadata.animal.PolarBearMeta

@Serializable
public object MetaPolarBear {
  @Serializable
  @SerialName("MetaPolarBear_setStandingUp")
  public data class StandingUp(
    public val arg0: Boolean
  ) : MobMeta() {
    public override fun apply(entity: Entity): Unit {
      (entity.entityMeta as? PolarBearMeta ?: return).setStandingUp(arg0)
    }
  }
}
