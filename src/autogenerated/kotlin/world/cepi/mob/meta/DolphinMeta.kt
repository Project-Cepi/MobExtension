package world.cepi.mob.meta

import kotlin.Boolean
import kotlin.Unit
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import net.minestom.server.coordinate.Point
import net.minestom.server.entity.Entity
import net.minestom.server.entity.metadata.MobMeta

public object DolphinMeta {
  @Serializable
  @SerialName("DolphinMeta_setTreasurePosition")
  public data class TreasurePosition(
    arg0: Point
  ) : MobMeta() {
    public override fun apply(entity: Entity): Unit {
      (entity as? net.minestom.server.entity.metadata.water.DolphinMeta ?:
          return).setTreasurePosition(arg0)
    }
  }

  @Serializable
  @SerialName("DolphinMeta_setCanFindTreasure")
  public data class CanFindTreasure(
    arg0: Boolean
  ) : MobMeta() {
    public override fun apply(entity: Entity): Unit {
      (entity as? net.minestom.server.entity.metadata.water.DolphinMeta ?:
          return).setCanFindTreasure(arg0)
    }
  }

  @Serializable
  @SerialName("DolphinMeta_setHasFish")
  public data class HasFish(
    arg0: Boolean
  ) : MobMeta() {
    public override fun apply(entity: Entity): Unit {
      (entity as? net.minestom.server.entity.metadata.water.DolphinMeta ?: return).setHasFish(arg0)
    }
  }
}
