package world.cepi.mob.meta

import kotlin.Boolean
import kotlin.Unit
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import net.minestom.server.entity.Entity
import net.minestom.server.entity.metadata.MobMeta

@Serializable
public object FishingHookMeta {
  @Serializable
  @SerialName("FishingHookMeta_setHookedEntity")
  public data class HookedEntity(
    public val arg0: Entity
  ) : MobMeta() {
    public override fun apply(entity: Entity): Unit {
      (entity as? net.minestom.server.entity.metadata.other.FishingHookMeta ?:
          return).setHookedEntity(arg0)
    }
  }

  @Serializable
  @SerialName("FishingHookMeta_setCatchable")
  public data class Catchable(
    public val arg0: Boolean
  ) : MobMeta() {
    public override fun apply(entity: Entity): Unit {
      (entity as? net.minestom.server.entity.metadata.other.FishingHookMeta ?:
          return).setCatchable(arg0)
    }
  }

  @Serializable
  @SerialName("FishingHookMeta_setOwnerEntity")
  public data class OwnerEntity(
    public val arg0: Entity
  ) : MobMeta() {
    public override fun apply(entity: Entity): Unit {
      (entity as? net.minestom.server.entity.metadata.other.FishingHookMeta ?:
          return).setOwnerEntity(arg0)
    }
  }
}
