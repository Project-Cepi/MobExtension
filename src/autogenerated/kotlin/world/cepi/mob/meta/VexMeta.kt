package world.cepi.mob.meta

import kotlin.Boolean
import kotlin.Unit
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import net.minestom.server.entity.Entity
import net.minestom.server.entity.metadata.MobMeta

public object VexMeta {
  @Serializable
  @SerialName("VexMeta_setAttacking")
  public data class Attacking(
    arg0: Boolean
  ) : MobMeta() {
    public override fun apply(entity: Entity): Unit {
      (entity as? net.minestom.server.entity.metadata.monster.VexMeta ?: return).setAttacking(arg0)
    }
  }
}
