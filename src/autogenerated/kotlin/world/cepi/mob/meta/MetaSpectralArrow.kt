package world.cepi.mob.meta

import kotlin.Unit
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import net.minestom.server.entity.Entity
import net.minestom.server.entity.metadata.arrow.SpectralArrowMeta

@Serializable
public object MetaSpectralArrow {
  @Serializable
  @SerialName("MetaSpectralArrow_setShooter")
  public data class Shooter(
    public val arg0: Entity
  ) : MobMeta() {
    public override fun apply(entity: Entity): Unit {
      (entity as? SpectralArrowMeta ?: return).setShooter(arg0)
    }
  }
}
