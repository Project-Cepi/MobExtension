package world.cepi.mob.meta

import kotlin.Int
import kotlin.Unit
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import net.minestom.server.entity.Entity
import net.minestom.server.entity.metadata.animal.LlamaMeta

@Serializable
public object MetaLlama {
  @Serializable
  @SerialName("MetaLlama_setVariant")
  public data class Variant(
    public val arg0: LlamaMeta.Variant
  ) : MobMeta() {
    public override fun apply(entity: Entity): Unit {
      (entity.entityMeta as? LlamaMeta ?: return).setVariant(arg0)
    }
  }

  @Serializable
  @SerialName("MetaLlama_setStrength")
  public data class Strength(
    public val arg0: Int
  ) : MobMeta() {
    public override fun apply(entity: Entity): Unit {
      (entity.entityMeta as? LlamaMeta ?: return).setStrength(arg0)
    }
  }

  @Serializable
  @SerialName("MetaLlama_setCarpetColor")
  public data class CarpetColor(
    public val arg0: Int
  ) : MobMeta() {
    public override fun apply(entity: Entity): Unit {
      (entity.entityMeta as? LlamaMeta ?: return).setCarpetColor(arg0)
    }
  }
}
