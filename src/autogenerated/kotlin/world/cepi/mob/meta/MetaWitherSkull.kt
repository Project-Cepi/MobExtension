package world.cepi.mob.meta

import kotlin.Boolean
import kotlin.Unit
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import net.minestom.server.entity.Entity
import net.minestom.server.entity.metadata.other.WitherSkullMeta

@Serializable
public object MetaWitherSkull {
  @Serializable
  @SerialName("MetaWitherSkull_setInvulnerable")
  public data class Invulnerable(
    public val arg0: Boolean
  ) : MobMeta() {
    public override fun apply(entity: Entity): Unit {
      (entity as? WitherSkullMeta ?: return).setInvulnerable(arg0)
    }
  }
}
