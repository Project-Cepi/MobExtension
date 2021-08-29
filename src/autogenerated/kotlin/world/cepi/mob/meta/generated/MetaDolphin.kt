package world.cepi.mob.meta.generated

import kotlin.Boolean
import kotlin.Unit
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import net.minestom.server.coordinate.Point
import net.minestom.server.entity.Entity
import net.minestom.server.entity.metadata.water.DolphinMeta
import world.cepi.mob.meta.MobMeta

@Serializable
public object MetaDolphin {
  @Serializable
  @SerialName("MetaDolphin_setTreasurePosition")
  public data class TreasurePosition(
    public val arg0: Point
  ) : MobMeta() {
    public override fun apply(entity: Entity): Unit {
      (entity.entityMeta as? DolphinMeta ?: return).setTreasurePosition(arg0)
    }
  }

  @Serializable
  @SerialName("MetaDolphin_setCanFindTreasure")
  public data class CanFindTreasure(
    public val arg0: Boolean
  ) : MobMeta() {
    public override fun apply(entity: Entity): Unit {
      (entity.entityMeta as? DolphinMeta ?: return).setCanFindTreasure(arg0)
    }
  }

  @Serializable
  @SerialName("MetaDolphin_setHasFish")
  public data class HasFish(
    public val arg0: Boolean
  ) : MobMeta() {
    public override fun apply(entity: Entity): Unit {
      (entity.entityMeta as? DolphinMeta ?: return).setHasFish(arg0)
    }
  }
}
