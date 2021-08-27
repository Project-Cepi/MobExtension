package world.cepi.mob.meta

import kotlin.Int
import kotlin.Unit
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import net.minestom.server.entity.Entity
import net.minestom.server.entity.metadata.MobMeta

@Serializable
public object PrimedTntMeta {
  @Serializable
  @SerialName("PrimedTntMeta_setFuseTime")
  public data class FuseTime(
    public val arg0: Int
  ) : MobMeta() {
    public override fun apply(entity: Entity): Unit {
      (entity as? net.minestom.server.entity.metadata.other.PrimedTntMeta ?:
          return).setFuseTime(arg0)
    }
  }
}
