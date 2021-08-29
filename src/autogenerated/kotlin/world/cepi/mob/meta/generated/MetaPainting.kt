package world.cepi.mob.meta.generated

import kotlin.Unit
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import net.minestom.server.entity.Entity
import net.minestom.server.entity.metadata.other.PaintingMeta
import world.cepi.mob.meta.MobMeta

@Serializable
public object MetaPainting {
  @Serializable
  @SerialName("MetaPainting_setMotive")
  public data class Motive(
    public val arg0: PaintingMeta.Motive
  ) : MobMeta() {
    public override fun apply(entity: Entity): Unit {
      (entity.entityMeta as? PaintingMeta ?: return).setMotive(arg0)
    }
  }

  @Serializable
  @SerialName("MetaPainting_setDirection")
  public data class Direction(
    public val arg0: net.minestom.server.utils.Direction
  ) : MobMeta() {
    public override fun apply(entity: Entity): Unit {
      (entity.entityMeta as? PaintingMeta ?: return).setDirection(arg0)
    }
  }
}
