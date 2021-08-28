package world.cepi.mob.meta

import kotlin.Int
import kotlin.Unit
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import net.minestom.server.entity.Entity
import net.minestom.server.entity.metadata.other.ExperienceOrbMeta

@Serializable
public object MetaExperienceOrb {
  @Serializable
  @SerialName("MetaExperienceOrb_setCount")
  public data class Count(
    public val arg0: Int
  ) : MobMeta() {
    public override fun apply(entity: Entity): Unit {
      (entity as? ExperienceOrbMeta ?: return).setCount(arg0)
    }
  }
}
