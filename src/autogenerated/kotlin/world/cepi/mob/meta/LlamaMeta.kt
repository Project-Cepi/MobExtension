package world.cepi.mob.meta

import kotlin.Int
import kotlin.Unit
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import net.minestom.server.entity.Entity
import net.minestom.server.entity.metadata.MobMeta

@Serializable
public object LlamaMeta {
  @Serializable
  @SerialName("LlamaMeta_setVariant")
  public data class Variant(
    public val arg0: net.minestom.server.entity.metadata.animal.LlamaMeta.Variant
  ) : MobMeta() {
    public override fun apply(entity: Entity): Unit {
      (entity as? net.minestom.server.entity.metadata.animal.LlamaMeta ?: return).setVariant(arg0)
    }
  }

  @Serializable
  @SerialName("LlamaMeta_setStrength")
  public data class Strength(
    public val arg0: Int
  ) : MobMeta() {
    public override fun apply(entity: Entity): Unit {
      (entity as? net.minestom.server.entity.metadata.animal.LlamaMeta ?: return).setStrength(arg0)
    }
  }

  @Serializable
  @SerialName("LlamaMeta_setCarpetColor")
  public data class CarpetColor(
    public val arg0: Int
  ) : MobMeta() {
    public override fun apply(entity: Entity): Unit {
      (entity as? net.minestom.server.entity.metadata.animal.LlamaMeta ?:
          return).setCarpetColor(arg0)
    }
  }
}
