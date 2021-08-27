package world.cepi.mob.meta

import kotlin.Unit
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import net.minestom.server.entity.Entity
import net.minestom.server.entity.metadata.MobMeta

@Serializable
public object SpectralArrowMeta {
  @Serializable
  @SerialName("SpectralArrowMeta_setShooter")
  public data class Shooter(
    public val arg0: Entity
  ) : MobMeta() {
    public override fun apply(entity: Entity): Unit {
      (entity as? net.minestom.server.entity.metadata.arrow.SpectralArrowMeta ?:
          return).setShooter(arg0)
    }
  }
}
