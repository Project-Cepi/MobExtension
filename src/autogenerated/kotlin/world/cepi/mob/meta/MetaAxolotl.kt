package world.cepi.mob.meta

import kotlin.Boolean
import kotlin.Unit
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import net.minestom.server.entity.Entity
import net.minestom.server.entity.metadata.water.AxolotlMeta

@Serializable
public object MetaAxolotl {
  @Serializable
  @SerialName("MetaAxolotl_setVariant")
  public data class Variant(
    public val arg0: AxolotlMeta.Variant
  ) : MobMeta() {
    public override fun apply(entity: Entity): Unit {
      (entity.entityMeta as? AxolotlMeta ?: return).setVariant(arg0)
    }
  }

  @Serializable
  @SerialName("MetaAxolotl_setFromBucket")
  public data class FromBucket(
    public val arg0: Boolean
  ) : MobMeta() {
    public override fun apply(entity: Entity): Unit {
      (entity.entityMeta as? AxolotlMeta ?: return).setFromBucket(arg0)
    }
  }

  @Serializable
  @SerialName("MetaAxolotl_setPlayingDead")
  public data class PlayingDead(
    public val arg0: Boolean
  ) : MobMeta() {
    public override fun apply(entity: Entity): Unit {
      (entity.entityMeta as? AxolotlMeta ?: return).setPlayingDead(arg0)
    }
  }
}
