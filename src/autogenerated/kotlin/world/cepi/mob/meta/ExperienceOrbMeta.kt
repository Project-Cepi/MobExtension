package world.cepi.mob.meta

import kotlin.Int
import kotlin.Unit
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import net.minestom.server.entity.Entity
import net.minestom.server.entity.metadata.MobMeta

public object ExperienceOrbMeta {
  @Serializable
  @SerialName("ExperienceOrbMeta_setCount")
  public data class Count(
    arg0: Int
  ) : MobMeta() {
    public override fun apply(entity: Entity): Unit {
      (entity as? net.minestom.server.entity.metadata.other.ExperienceOrbMeta ?:
          return).setCount(arg0)
    }
  }
}
