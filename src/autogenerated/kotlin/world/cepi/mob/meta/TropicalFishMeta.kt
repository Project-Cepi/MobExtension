package world.cepi.mob.meta

import kotlin.Int
import kotlin.Unit
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import net.minestom.server.entity.Entity
import net.minestom.server.entity.metadata.MobMeta

@Serializable
public object TropicalFishMeta {
  @Serializable
  @SerialName("TropicalFishMeta_setVariant")
  public data class Variant(
    public val arg0: net.minestom.server.entity.metadata.water.fish.TropicalFishMeta.Variant
  ) : MobMeta() {
    public override fun apply(entity: Entity): Unit {
      (entity as? net.minestom.server.entity.metadata.water.fish.TropicalFishMeta ?:
          return).setVariant(arg0)
    }
  }

  @Serializable
  @SerialName("TropicalFishMeta_getVariantID")
  public data class VariantID(
    public val arg0: net.minestom.server.entity.metadata.water.fish.TropicalFishMeta.Variant
  ) : MobMeta() {
    public override fun apply(entity: Entity): Unit {
      (entity as? net.minestom.server.entity.metadata.water.fish.TropicalFishMeta ?:
          return).getVariantID(arg0)
    }
  }

  @Serializable
  @SerialName("TropicalFishMeta_getVariantFromID")
  public data class VariantFromID(
    public val arg0: Int
  ) : MobMeta() {
    public override fun apply(entity: Entity): Unit {
      (entity as? net.minestom.server.entity.metadata.water.fish.TropicalFishMeta ?:
          return).getVariantFromID(arg0)
    }
  }
}
