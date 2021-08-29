package world.cepi.mob.meta.generated

import kotlin.Boolean
import kotlin.Unit
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import net.minestom.server.entity.Entity
import world.cepi.mob.meta.MobMeta

@Serializable
public object MetaMob {
  @Serializable
  @SerialName("MetaMob_setNoAi")
  public data class NoAi(
    public val arg0: Boolean
  ) : MobMeta() {
    public override fun apply(entity: Entity): Unit {
      (entity.entityMeta as? net.minestom.server.entity.metadata.MobMeta ?: return).setNoAi(arg0)
    }
  }

  @Serializable
  @SerialName("MetaMob_setLeftHanded")
  public data class LeftHanded(
    public val arg0: Boolean
  ) : MobMeta() {
    public override fun apply(entity: Entity): Unit {
      (entity.entityMeta as? net.minestom.server.entity.metadata.MobMeta ?:
          return).setLeftHanded(arg0)
    }
  }

  @Serializable
  @SerialName("MetaMob_setAggressive")
  public data class Aggressive(
    public val arg0: Boolean
  ) : MobMeta() {
    public override fun apply(entity: Entity): Unit {
      (entity.entityMeta as? net.minestom.server.entity.metadata.MobMeta ?:
          return).setAggressive(arg0)
    }
  }
}
