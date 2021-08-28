package world.cepi.mob.meta

import kotlin.Int
import kotlin.Unit
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import net.minestom.server.entity.Entity
import net.minestom.server.entity.metadata.water.GlowSquidMeta

@Serializable
public object MetaGlowSquid {
  @Serializable
  @SerialName("MetaGlowSquid_setDarkTicksRemaining")
  public data class DarkTicksRemaining(
    public val arg0: Int
  ) : MobMeta() {
    public override fun apply(entity: Entity): Unit {
      (entity as? GlowSquidMeta ?: return).setDarkTicksRemaining(arg0)
    }
  }
}
