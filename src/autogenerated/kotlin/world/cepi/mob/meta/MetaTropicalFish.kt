package world.cepi.mob.meta

import kotlin.Int
import kotlin.Unit
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import net.minestom.server.entity.Entity
import net.minestom.server.entity.metadata.water.fish.TropicalFishMeta

@Serializable
public object MetaTropicalFish {
  @Serializable
  @SerialName("MetaTropicalFish_setVariant")
  public data class Variant(
    public val arg0: TropicalFishMeta.Variant
  ) : MobMeta() {
    public override fun apply(entity: Entity): Unit {
      (entity as? TropicalFishMeta ?: return).setVariant(arg0)
    }
  }

  @Serializable
  @SerialName("MetaTropicalFish_getVariantID")
  public data class VariantID(
    public val arg0: TropicalFishMeta.Variant
  ) : MobMeta() {
    public override fun apply(entity: Entity): Unit {
      (entity as? TropicalFishMeta ?: return).getVariantID(arg0)
    }
  }

  @Serializable
  @SerialName("MetaTropicalFish_getVariantFromID")
  public data class VariantFromID(
    public val arg0: Int
  ) : MobMeta() {
    public override fun apply(entity: Entity): Unit {
      (entity as? TropicalFishMeta ?: return).getVariantFromID(arg0)
    }
  }
}
