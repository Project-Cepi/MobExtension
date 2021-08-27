package world.cepi.mob.meta

import kotlin.Unit
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import net.minestom.server.entity.Entity
import net.minestom.server.entity.metadata.MobMeta

public object MooshroomMeta {
  @Serializable
  @SerialName("MooshroomMeta_setVariant")
  public data class Variant(
    arg0: net.minestom.server.entity.metadata.animal.MooshroomMeta.Variant
  ) : MobMeta() {
    public override fun apply(entity: Entity): Unit {
      (entity as? net.minestom.server.entity.metadata.animal.MooshroomMeta ?:
          return).setVariant(arg0)
    }
  }
}
