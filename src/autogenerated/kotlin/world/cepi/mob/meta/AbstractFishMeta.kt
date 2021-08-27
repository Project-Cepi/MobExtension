package world.cepi.mob.meta

import kotlin.Boolean
import kotlin.Unit
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import net.minestom.server.entity.Entity
import net.minestom.server.entity.metadata.MobMeta

@Serializable
public object AbstractFishMeta {
  @Serializable
  @SerialName("AbstractFishMeta_setFromBucket")
  public data class FromBucket(
    public val arg0: Boolean
  ) : MobMeta() {
    public override fun apply(entity: Entity): Unit {
      (entity as? net.minestom.server.entity.metadata.water.fish.AbstractFishMeta ?:
          return).setFromBucket(arg0)
    }
  }
}
