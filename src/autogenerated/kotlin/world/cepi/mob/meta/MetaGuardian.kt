package world.cepi.mob.meta

import kotlin.Boolean
import kotlin.Unit
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import net.minestom.server.entity.Entity
import net.minestom.server.entity.metadata.monster.GuardianMeta

@Serializable
public object MetaGuardian {
  @Serializable
  @SerialName("MetaGuardian_setRetractingSpikes")
  public data class RetractingSpikes(
    public val arg0: Boolean
  ) : MobMeta() {
    public override fun apply(entity: Entity): Unit {
      (entity as? GuardianMeta ?: return).setRetractingSpikes(arg0)
    }
  }
}
