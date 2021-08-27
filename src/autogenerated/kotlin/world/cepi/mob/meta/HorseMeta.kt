package world.cepi.mob.meta

import kotlin.Int
import kotlin.Unit
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import net.minestom.server.entity.Entity
import net.minestom.server.entity.metadata.MobMeta

public object HorseMeta {
  @Serializable
  @SerialName("HorseMeta_setVariant")
  public data class Variant(
    arg0: net.minestom.server.entity.metadata.animal.HorseMeta.Variant
  ) : MobMeta() {
    public override fun apply(entity: Entity): Unit {
      (entity as? net.minestom.server.entity.metadata.animal.HorseMeta ?: return).setVariant(arg0)
    }
  }

  @Serializable
  @SerialName("HorseMeta_getVariantID")
  public data class VariantID(
    arg0: net.minestom.server.entity.metadata.animal.HorseMeta.Marking,
    arg1: net.minestom.server.entity.metadata.animal.HorseMeta.Color
  ) : MobMeta() {
    public override fun apply(entity: Entity): Unit {
      (entity as? net.minestom.server.entity.metadata.animal.HorseMeta ?: return).getVariantID(arg0,
          arg1)
    }
  }

  @Serializable
  @SerialName("HorseMeta_getVariantFromID")
  public data class VariantFromID(
    arg0: Int
  ) : MobMeta() {
    public override fun apply(entity: Entity): Unit {
      (entity as? net.minestom.server.entity.metadata.animal.HorseMeta ?:
          return).getVariantFromID(arg0)
    }
  }
}
