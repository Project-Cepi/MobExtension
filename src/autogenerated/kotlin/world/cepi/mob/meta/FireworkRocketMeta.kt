package world.cepi.mob.meta

import kotlin.Boolean
import kotlin.Unit
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import net.minestom.server.entity.Entity
import net.minestom.server.entity.metadata.MobMeta
import net.minestom.server.item.ItemStack

public object FireworkRocketMeta {
  @Serializable
  @SerialName("FireworkRocketMeta_setShooter")
  public data class Shooter(
    arg0: Entity
  ) : MobMeta() {
    public override fun apply(entity: Entity): Unit {
      (entity as? net.minestom.server.entity.metadata.other.FireworkRocketMeta ?:
          return).setShooter(arg0)
    }
  }

  @Serializable
  @SerialName("FireworkRocketMeta_setFireworkInfo")
  public data class FireworkInfo(
    arg0: ItemStack
  ) : MobMeta() {
    public override fun apply(entity: Entity): Unit {
      (entity as? net.minestom.server.entity.metadata.other.FireworkRocketMeta ?:
          return).setFireworkInfo(arg0)
    }
  }

  @Serializable
  @SerialName("FireworkRocketMeta_setShotAtAngle")
  public data class ShotAtAngle(
    arg0: Boolean
  ) : MobMeta() {
    public override fun apply(entity: Entity): Unit {
      (entity as? net.minestom.server.entity.metadata.other.FireworkRocketMeta ?:
          return).setShotAtAngle(arg0)
    }
  }
}
