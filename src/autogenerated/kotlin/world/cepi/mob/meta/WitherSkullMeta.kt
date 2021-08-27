package world.cepi.mob.meta

import kotlin.Boolean
import kotlin.Unit
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import net.minestom.server.entity.Entity
import net.minestom.server.entity.metadata.MobMeta

public object WitherSkullMeta {
  @Serializable
  @SerialName("WitherSkullMeta_setInvulnerable")
  public data class Invulnerable(
    arg0: Boolean
  ) : MobMeta() {
    public override fun apply(entity: Entity): Unit {
      (entity as? net.minestom.server.entity.metadata.other.WitherSkullMeta ?:
          return).setInvulnerable(arg0)
    }
  }

  @Serializable
  @SerialName("WitherSkullMeta_setShooter")
  public data class Shooter(
    arg0: Entity
  ) : MobMeta() {
    public override fun apply(entity: Entity): Unit {
      (entity as? net.minestom.server.entity.metadata.other.WitherSkullMeta ?:
          return).setShooter(arg0)
    }
  }
}
