package world.cepi.mob.meta

import kotlin.Boolean
import kotlin.Unit
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import net.minestom.server.entity.Entity
import net.minestom.server.entity.metadata.MobMeta

public object GuardianMeta {
  @Serializable
  @SerialName("GuardianMeta_setTarget")
  public data class Target(
    arg0: Entity
  ) : MobMeta() {
    public override fun apply(entity: Entity): Unit {
      (entity as? net.minestom.server.entity.metadata.monster.GuardianMeta ?:
          return).setTarget(arg0)
    }
  }

  @Serializable
  @SerialName("GuardianMeta_setRetractingSpikes")
  public data class RetractingSpikes(
    arg0: Boolean
  ) : MobMeta() {
    public override fun apply(entity: Entity): Unit {
      (entity as? net.minestom.server.entity.metadata.monster.GuardianMeta ?:
          return).setRetractingSpikes(arg0)
    }
  }
}
