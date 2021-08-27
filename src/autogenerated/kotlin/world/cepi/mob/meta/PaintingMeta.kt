package world.cepi.mob.meta

import kotlin.Unit
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import net.minestom.server.entity.Entity
import net.minestom.server.entity.metadata.MobMeta

public object PaintingMeta {
  @Serializable
  @SerialName("PaintingMeta_setMotive")
  public data class Motive(
    arg0: net.minestom.server.entity.metadata.other.PaintingMeta.Motive
  ) : MobMeta() {
    public override fun apply(entity: Entity): Unit {
      (entity as? net.minestom.server.entity.metadata.other.PaintingMeta ?: return).setMotive(arg0)
    }
  }

  @Serializable
  @SerialName("PaintingMeta_setDirection")
  public data class Direction(
    arg0: net.minestom.server.utils.Direction
  ) : MobMeta() {
    public override fun apply(entity: Entity): Unit {
      (entity as? net.minestom.server.entity.metadata.other.PaintingMeta ?:
          return).setDirection(arg0)
    }
  }
}
