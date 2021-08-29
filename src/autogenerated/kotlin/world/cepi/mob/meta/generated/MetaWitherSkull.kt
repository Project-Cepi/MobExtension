package world.cepi.mob.meta.generated

import kotlin.Boolean
import kotlin.Unit
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import net.minestom.server.entity.Entity
import net.minestom.server.entity.metadata.other.WitherSkullMeta
import world.cepi.mob.meta.MobMeta

@Serializable
public object MetaWitherSkull {
  @Serializable
  @SerialName("MetaWitherSkull_setInvulnerable")
  public data class Invulnerable(
    public val arg0: Boolean
  ) : MobMeta() {
    public override fun apply(entity: Entity): Unit {
      (entity.entityMeta as? WitherSkullMeta ?: return).setInvulnerable(arg0)
    }
  }
}
