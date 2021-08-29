package world.cepi.mob.meta.generated

import kotlin.Unit
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import net.minestom.server.entity.Entity
import net.minestom.server.entity.metadata.animal.MooshroomMeta
import world.cepi.mob.meta.MobMeta

@Serializable
public object MetaMooshroom {
  @Serializable
  @SerialName("MetaMooshroom_setVariant")
  public data class Variant(
    public val arg0: MooshroomMeta.Variant
  ) : MobMeta() {
    public override fun apply(entity: Entity): Unit {
      (entity.entityMeta as? MooshroomMeta ?: return).setVariant(arg0)
    }
  }
}
