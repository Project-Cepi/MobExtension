package world.cepi.mob.meta

import kotlin.Boolean
import kotlin.Unit
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import net.minestom.server.coordinate.Point
import net.minestom.server.entity.Entity
import net.minestom.server.entity.metadata.animal.TurtleMeta

@Serializable
public object MetaTurtle {
  @Serializable
  @SerialName("MetaTurtle_setBlockPosition")
  public data class BlockPosition(
    public val arg0: Point
  ) : MobMeta() {
    public override fun apply(entity: Entity): Unit {
      (entity.entityMeta as? TurtleMeta ?: return).setBlockPosition(arg0)
    }
  }

  @Serializable
  @SerialName("MetaTurtle_setHasEgg")
  public data class HasEgg(
    public val arg0: Boolean
  ) : MobMeta() {
    public override fun apply(entity: Entity): Unit {
      (entity.entityMeta as? TurtleMeta ?: return).setHasEgg(arg0)
    }
  }

  @Serializable
  @SerialName("MetaTurtle_setLayingEgg")
  public data class LayingEgg(
    public val arg0: Boolean
  ) : MobMeta() {
    public override fun apply(entity: Entity): Unit {
      (entity.entityMeta as? TurtleMeta ?: return).setLayingEgg(arg0)
    }
  }

  @Serializable
  @SerialName("MetaTurtle_setTravelPosition")
  public data class TravelPosition(
    public val arg0: Point
  ) : MobMeta() {
    public override fun apply(entity: Entity): Unit {
      (entity.entityMeta as? TurtleMeta ?: return).setTravelPosition(arg0)
    }
  }

  @Serializable
  @SerialName("MetaTurtle_setGoingHome")
  public data class GoingHome(
    public val arg0: Boolean
  ) : MobMeta() {
    public override fun apply(entity: Entity): Unit {
      (entity.entityMeta as? TurtleMeta ?: return).setGoingHome(arg0)
    }
  }

  @Serializable
  @SerialName("MetaTurtle_setTravelling")
  public data class Travelling(
    public val arg0: Boolean
  ) : MobMeta() {
    public override fun apply(entity: Entity): Unit {
      (entity.entityMeta as? TurtleMeta ?: return).setTravelling(arg0)
    }
  }
}
