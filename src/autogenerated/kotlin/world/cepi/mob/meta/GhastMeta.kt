package world.cepi.mob.meta

import kotlin.Boolean
import kotlin.Unit
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import net.minestom.server.entity.Entity
import net.minestom.server.entity.metadata.MobMeta

public object GhastMeta {
  @Serializable
  @SerialName("GhastMeta_setAttacking")
  public data class Attacking(
    arg0: Boolean
  ) : MobMeta() {
    public override fun apply(entity: Entity): Unit {
      (entity as? net.minestom.server.entity.metadata.flying.GhastMeta ?: return).setAttacking(arg0)
    }
  }
}
