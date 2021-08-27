package world.cepi.mob.meta

import kotlin.Boolean
import kotlin.Unit
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import net.minestom.server.entity.Entity

@Serializable
public object MobMeta {
  @Serializable
  @SerialName("MobMeta_setNoAi")
  public data class NoAi(
    public val arg0: Boolean
  ) : net.minestom.server.entity.metadata.MobMeta() {
    public override fun apply(entity: Entity): Unit {
      (entity as? net.minestom.server.entity.metadata.MobMeta ?: return).setNoAi(arg0)
    }
  }

  @Serializable
  @SerialName("MobMeta_setLeftHanded")
  public data class LeftHanded(
    public val arg0: Boolean
  ) : net.minestom.server.entity.metadata.MobMeta() {
    public override fun apply(entity: Entity): Unit {
      (entity as? net.minestom.server.entity.metadata.MobMeta ?: return).setLeftHanded(arg0)
    }
  }

  @Serializable
  @SerialName("MobMeta_setAggressive")
  public data class Aggressive(
    public val arg0: Boolean
  ) : net.minestom.server.entity.metadata.MobMeta() {
    public override fun apply(entity: Entity): Unit {
      (entity as? net.minestom.server.entity.metadata.MobMeta ?: return).setAggressive(arg0)
    }
  }
}
