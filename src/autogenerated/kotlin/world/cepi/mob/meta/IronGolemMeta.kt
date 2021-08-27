package world.cepi.mob.meta

import kotlin.Boolean
import kotlin.Unit
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import net.minestom.server.entity.Entity
import net.minestom.server.entity.metadata.MobMeta

@Serializable
public object IronGolemMeta {
  @Serializable
  @SerialName("IronGolemMeta_setPlayerCreated")
  public data class PlayerCreated(
    public val arg0: Boolean
  ) : MobMeta() {
    public override fun apply(entity: Entity): Unit {
      (entity as? net.minestom.server.entity.metadata.golem.IronGolemMeta ?:
          return).setPlayerCreated(arg0)
    }
  }
}
