package world.cepi.mob.meta

import kotlin.Unit
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import net.minestom.server.entity.Entity
import net.minestom.server.entity.metadata.MobMeta

public object EnderDragonMeta {
  @Serializable
  @SerialName("EnderDragonMeta_setPhase")
  public data class Phase(
    arg0: net.minestom.server.entity.metadata.other.EnderDragonMeta.Phase
  ) : MobMeta() {
    public override fun apply(entity: Entity): Unit {
      (entity as? net.minestom.server.entity.metadata.other.EnderDragonMeta ?:
          return).setPhase(arg0)
    }
  }
}
