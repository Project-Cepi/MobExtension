package world.cepi.mob.meta

import kotlin.Boolean
import kotlin.Unit
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import net.minestom.server.entity.Entity
import net.minestom.server.entity.metadata.flying.GhastMeta

@Serializable
public object MetaGhast {
  @Serializable
  @SerialName("MetaGhast_setAttacking")
  public data class Attacking(
    public val arg0: Boolean
  ) : MobMeta() {
    public override fun apply(entity: Entity): Unit {
      (entity as? GhastMeta ?: return).setAttacking(arg0)
    }
  }
}
