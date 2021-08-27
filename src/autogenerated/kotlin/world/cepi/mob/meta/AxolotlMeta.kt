package world.cepi.mob.meta

import kotlin.Boolean
import kotlin.Unit
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import net.minestom.server.entity.Entity
import net.minestom.server.entity.metadata.MobMeta

public object AxolotlMeta {
  @Serializable
  @SerialName("AxolotlMeta_setVariant")
  public data class Variant(
    arg0: net.minestom.server.entity.metadata.water.AxolotlMeta.Variant
  ) : MobMeta() {
    public override fun apply(entity: Entity): Unit {
      (entity as? net.minestom.server.entity.metadata.water.AxolotlMeta ?: return).setVariant(arg0)
    }
  }

  @Serializable
  @SerialName("AxolotlMeta_setFromBucket")
  public data class FromBucket(
    arg0: Boolean
  ) : MobMeta() {
    public override fun apply(entity: Entity): Unit {
      (entity as? net.minestom.server.entity.metadata.water.AxolotlMeta ?:
          return).setFromBucket(arg0)
    }
  }

  @Serializable
  @SerialName("AxolotlMeta_setPlayingDead")
  public data class PlayingDead(
    arg0: Boolean
  ) : MobMeta() {
    public override fun apply(entity: Entity): Unit {
      (entity as? net.minestom.server.entity.metadata.water.AxolotlMeta ?:
          return).setPlayingDead(arg0)
    }
  }
}
