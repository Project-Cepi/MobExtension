package world.cepi.mob.meta

import kotlin.Boolean
import kotlin.Unit
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import net.minestom.server.entity.Entity
import net.minestom.server.entity.metadata.golem.SnowGolemMeta

@Serializable
public object MetaSnowGolem {
  @Serializable
  @SerialName("MetaSnowGolem_setHasPumpkinHat")
  public data class HasPumpkinHat(
    public val arg0: Boolean
  ) : MobMeta() {
    public override fun apply(entity: Entity): Unit {
      (entity.entityMeta as? SnowGolemMeta ?: return).setHasPumpkinHat(arg0)
    }
  }
}
