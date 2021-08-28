package world.cepi.mob.meta

import kotlin.Unit
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import net.minestom.server.entity.Entity
import net.minestom.server.entity.metadata.other.DragonFireballMeta

@Serializable
public object MetaDragonFireball {
  @Serializable
  @SerialName("MetaDragonFireball_setShooter")
  public data class Shooter(
    public val arg0: Entity
  ) : MobMeta() {
    public override fun apply(entity: Entity): Unit {
      (entity as? DragonFireballMeta ?: return).setShooter(arg0)
    }
  }
}
