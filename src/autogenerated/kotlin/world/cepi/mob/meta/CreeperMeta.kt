package world.cepi.mob.meta

import kotlin.Boolean
import kotlin.Unit
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import net.minestom.server.entity.Entity
import net.minestom.server.entity.metadata.MobMeta

public object CreeperMeta {
  @Serializable
  @SerialName("CreeperMeta_setState")
  public data class State(
    arg0: net.minestom.server.entity.metadata.monster.CreeperMeta.State
  ) : MobMeta() {
    public override fun apply(entity: Entity): Unit {
      (entity as? net.minestom.server.entity.metadata.monster.CreeperMeta ?: return).setState(arg0)
    }
  }

  @Serializable
  @SerialName("CreeperMeta_setCharged")
  public data class Charged(
    arg0: Boolean
  ) : MobMeta() {
    public override fun apply(entity: Entity): Unit {
      (entity as? net.minestom.server.entity.metadata.monster.CreeperMeta ?:
          return).setCharged(arg0)
    }
  }

  @Serializable
  @SerialName("CreeperMeta_setIgnited")
  public data class Ignited(
    arg0: Boolean
  ) : MobMeta() {
    public override fun apply(entity: Entity): Unit {
      (entity as? net.minestom.server.entity.metadata.monster.CreeperMeta ?:
          return).setIgnited(arg0)
    }
  }
}
