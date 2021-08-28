package world.cepi.mob.meta

import kotlin.Int
import kotlin.Unit
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import net.minestom.server.entity.Entity
import net.minestom.server.entity.metadata.other.PrimedTntMeta

@Serializable
public object MetaPrimedTnt {
  @Serializable
  @SerialName("MetaPrimedTnt_setFuseTime")
  public data class FuseTime(
    public val arg0: Int
  ) : MobMeta() {
    public override fun apply(entity: Entity): Unit {
      (entity.entityMeta as? PrimedTntMeta ?: return).setFuseTime(arg0)
    }
  }
}
