package world.cepi.mob.meta

import kotlin.Boolean
import kotlin.Unit
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import net.minestom.server.entity.Entity
import net.minestom.server.entity.metadata.animal.HoglinMeta

@Serializable
public object MetaHoglin {
  @Serializable
  @SerialName("MetaHoglin_setImmuneToZombification")
  public data class ImmuneToZombification(
    public val arg0: Boolean
  ) : MobMeta() {
    public override fun apply(entity: Entity): Unit {
      (entity.entityMeta as? HoglinMeta ?: return).setImmuneToZombification(arg0)
    }
  }
}
