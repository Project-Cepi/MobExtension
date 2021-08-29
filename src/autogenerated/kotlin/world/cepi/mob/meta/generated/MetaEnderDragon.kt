package world.cepi.mob.meta.generated

import kotlin.Unit
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import net.minestom.server.entity.Entity
import net.minestom.server.entity.metadata.other.EnderDragonMeta
import world.cepi.mob.meta.MobMeta

@Serializable
public object MetaEnderDragon {
  @Serializable
  @SerialName("MetaEnderDragon_setPhase")
  public data class Phase(
    public val arg0: EnderDragonMeta.Phase
  ) : MobMeta() {
    public override fun apply(entity: Entity): Unit {
      (entity.entityMeta as? EnderDragonMeta ?: return).setPhase(arg0)
    }
  }
}
