package world.cepi.mob.meta

import kotlin.Unit
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import net.minestom.server.entity.Entity
import net.minestom.server.entity.metadata.animal.MooshroomMeta

@Serializable
public object MetaMooshroom {
  @Serializable
  @SerialName("MetaMooshroom_setVariant")
  public data class Variant(
    public val arg0: MooshroomMeta.Variant
  ) : MobMeta() {
    public override fun apply(entity: Entity): Unit {
      (entity as? MooshroomMeta ?: return).setVariant(arg0)
    }
  }
}
