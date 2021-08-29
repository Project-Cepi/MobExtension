package world.cepi.mob.meta.generated

import kotlin.Boolean
import kotlin.Unit
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import net.minestom.server.entity.Entity
import net.minestom.server.entity.metadata.animal.OcelotMeta
import world.cepi.mob.meta.MobMeta

@Serializable
public object MetaOcelot {
  @Serializable
  @SerialName("MetaOcelot_setTrusting")
  public data class Trusting(
    public val arg0: Boolean
  ) : MobMeta() {
    public override fun apply(entity: Entity): Unit {
      (entity.entityMeta as? OcelotMeta ?: return).setTrusting(arg0)
    }
  }
}
