package world.cepi.mob.meta

import kotlin.Boolean
import kotlin.Unit
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import net.minestom.server.entity.Entity
import net.minestom.server.entity.metadata.animal.OcelotMeta

@Serializable
public object MetaOcelot {
  @Serializable
  @SerialName("MetaOcelot_setTrusting")
  public data class Trusting(
    public val arg0: Boolean
  ) : MobMeta() {
    public override fun apply(entity: Entity): Unit {
      (entity as? OcelotMeta ?: return).setTrusting(arg0)
    }
  }
}
