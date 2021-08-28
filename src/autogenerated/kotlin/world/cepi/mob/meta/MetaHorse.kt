package world.cepi.mob.meta

import kotlin.Int
import kotlin.Unit
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import net.minestom.server.entity.Entity
import net.minestom.server.entity.metadata.animal.HorseMeta

@Serializable
public object MetaHorse {
  @Serializable
  @SerialName("MetaHorse_getVariantID")
  public data class VariantID(
    public val arg0: HorseMeta.Marking,
    public val arg1: HorseMeta.Color
  ) : MobMeta() {
    public override fun apply(entity: Entity): Unit {
      (entity as? HorseMeta ?: return).getVariantID(arg0, arg1)
    }
  }

  @Serializable
  @SerialName("MetaHorse_getVariantFromID")
  public data class VariantFromID(
    public val arg0: Int
  ) : MobMeta() {
    public override fun apply(entity: Entity): Unit {
      (entity as? HorseMeta ?: return).getVariantFromID(arg0)
    }
  }
}
