package world.cepi.mob.meta

import kotlin.Boolean
import kotlin.Unit
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import net.minestom.server.entity.Entity
import net.minestom.server.entity.metadata.water.fish.AbstractFishMeta

@Serializable
public object MetaAbstractFish {
  @Serializable
  @SerialName("MetaAbstractFish_setFromBucket")
  public data class FromBucket(
    public val arg0: Boolean
  ) : MobMeta() {
    public override fun apply(entity: Entity): Unit {
      (entity.entityMeta as? AbstractFishMeta ?: return).setFromBucket(arg0)
    }
  }
}
