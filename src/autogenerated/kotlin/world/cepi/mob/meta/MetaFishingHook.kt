package world.cepi.mob.meta

import kotlin.Boolean
import kotlin.Unit
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import net.minestom.server.entity.Entity
import net.minestom.server.entity.metadata.other.FishingHookMeta

@Serializable
public object MetaFishingHook {
  @Serializable
  @SerialName("MetaFishingHook_setHookedEntity")
  public data class HookedEntity(
    public val arg0: Entity
  ) : MobMeta() {
    public override fun apply(entity: Entity): Unit {
      (entity as? FishingHookMeta ?: return).setHookedEntity(arg0)
    }
  }

  @Serializable
  @SerialName("MetaFishingHook_setCatchable")
  public data class Catchable(
    public val arg0: Boolean
  ) : MobMeta() {
    public override fun apply(entity: Entity): Unit {
      (entity as? FishingHookMeta ?: return).setCatchable(arg0)
    }
  }

  @Serializable
  @SerialName("MetaFishingHook_setOwnerEntity")
  public data class OwnerEntity(
    public val arg0: Entity
  ) : MobMeta() {
    public override fun apply(entity: Entity): Unit {
      (entity as? FishingHookMeta ?: return).setOwnerEntity(arg0)
    }
  }
}
