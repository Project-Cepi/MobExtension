package world.cepi.mob.meta

import kotlin.Boolean
import kotlin.Unit
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import net.minestom.server.coordinate.Point
import net.minestom.server.entity.Entity
import net.minestom.server.entity.metadata.MobMeta

@Serializable
public object TurtleMeta {
  @Serializable
  @SerialName("TurtleMeta_setBlockPosition")
  public data class BlockPosition(
    public val arg0: Point
  ) : MobMeta() {
    public override fun apply(entity: Entity): Unit {
      (entity as? net.minestom.server.entity.metadata.animal.TurtleMeta ?:
          return).setBlockPosition(arg0)
    }
  }

  @Serializable
  @SerialName("TurtleMeta_setHasEgg")
  public data class HasEgg(
    public val arg0: Boolean
  ) : MobMeta() {
    public override fun apply(entity: Entity): Unit {
      (entity as? net.minestom.server.entity.metadata.animal.TurtleMeta ?: return).setHasEgg(arg0)
    }
  }

  @Serializable
  @SerialName("TurtleMeta_setLayingEgg")
  public data class LayingEgg(
    public val arg0: Boolean
  ) : MobMeta() {
    public override fun apply(entity: Entity): Unit {
      (entity as? net.minestom.server.entity.metadata.animal.TurtleMeta ?:
          return).setLayingEgg(arg0)
    }
  }

  @Serializable
  @SerialName("TurtleMeta_setTravelPosition")
  public data class TravelPosition(
    public val arg0: Point
  ) : MobMeta() {
    public override fun apply(entity: Entity): Unit {
      (entity as? net.minestom.server.entity.metadata.animal.TurtleMeta ?:
          return).setTravelPosition(arg0)
    }
  }

  @Serializable
  @SerialName("TurtleMeta_setGoingHome")
  public data class GoingHome(
    public val arg0: Boolean
  ) : MobMeta() {
    public override fun apply(entity: Entity): Unit {
      (entity as? net.minestom.server.entity.metadata.animal.TurtleMeta ?:
          return).setGoingHome(arg0)
    }
  }

  @Serializable
  @SerialName("TurtleMeta_setTravelling")
  public data class Travelling(
    public val arg0: Boolean
  ) : MobMeta() {
    public override fun apply(entity: Entity): Unit {
      (entity as? net.minestom.server.entity.metadata.animal.TurtleMeta ?:
          return).setTravelling(arg0)
    }
  }
}
