package world.cepi.mob.meta

import kotlin.Unit
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import net.minestom.server.entity.Entity
import net.minestom.server.entity.metadata.MobMeta

public object DragonFireballMeta {
  @Serializable
  @SerialName("DragonFireballMeta_setShooter")
  public data class Shooter(
    arg0: Entity
  ) : MobMeta() {
    public override fun apply(entity: Entity): Unit {
      (entity as? net.minestom.server.entity.metadata.other.DragonFireballMeta ?:
          return).setShooter(arg0)
    }
  }
}
