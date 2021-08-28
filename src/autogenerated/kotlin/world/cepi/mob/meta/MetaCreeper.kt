package world.cepi.mob.meta

import kotlin.Boolean
import kotlin.Unit
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import net.minestom.server.entity.Entity
import net.minestom.server.entity.metadata.monster.CreeperMeta

@Serializable
public object MetaCreeper {
  @Serializable
  @SerialName("MetaCreeper_setState")
  public data class State(
    public val arg0: CreeperMeta.State
  ) : MobMeta() {
    public override fun apply(entity: Entity): Unit {
      (entity as? CreeperMeta ?: return).setState(arg0)
    }
  }

  @Serializable
  @SerialName("MetaCreeper_setCharged")
  public data class Charged(
    public val arg0: Boolean
  ) : MobMeta() {
    public override fun apply(entity: Entity): Unit {
      (entity as? CreeperMeta ?: return).setCharged(arg0)
    }
  }

  @Serializable
  @SerialName("MetaCreeper_setIgnited")
  public data class Ignited(
    public val arg0: Boolean
  ) : MobMeta() {
    public override fun apply(entity: Entity): Unit {
      (entity as? CreeperMeta ?: return).setIgnited(arg0)
    }
  }
}
