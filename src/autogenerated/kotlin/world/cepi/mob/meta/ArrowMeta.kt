package world.cepi.mob.meta

import kotlin.Int
import kotlin.Unit
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import net.minestom.server.entity.Entity
import net.minestom.server.entity.metadata.MobMeta

@Serializable
public object ArrowMeta {
  @Serializable
  @SerialName("ArrowMeta_setColor")
  public data class Color(
    public val arg0: Int
  ) : MobMeta() {
    public override fun apply(entity: Entity): Unit {
      (entity as? net.minestom.server.entity.metadata.arrow.ArrowMeta ?: return).setColor(arg0)
    }
  }

  @Serializable
  @SerialName("ArrowMeta_setShooter")
  public data class Shooter(
    public val arg0: Entity
  ) : MobMeta() {
    public override fun apply(entity: Entity): Unit {
      (entity as? net.minestom.server.entity.metadata.arrow.ArrowMeta ?: return).setShooter(arg0)
    }
  }
}
