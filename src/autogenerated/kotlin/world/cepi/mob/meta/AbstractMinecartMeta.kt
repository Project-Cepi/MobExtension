package world.cepi.mob.meta

import kotlin.Float
import kotlin.Int
import kotlin.Unit
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import net.minestom.server.entity.Entity
import net.minestom.server.entity.metadata.MobMeta

@Serializable
public object AbstractMinecartMeta {
  @Serializable
  @SerialName("AbstractMinecartMeta_setShakingPower")
  public data class ShakingPower(
    public val arg0: Int
  ) : MobMeta() {
    public override fun apply(entity: Entity): Unit {
      (entity as? net.minestom.server.entity.metadata.minecart.AbstractMinecartMeta ?:
          return).setShakingPower(arg0)
    }
  }

  @Serializable
  @SerialName("AbstractMinecartMeta_setShakingDirection")
  public data class ShakingDirection(
    public val arg0: Int
  ) : MobMeta() {
    public override fun apply(entity: Entity): Unit {
      (entity as? net.minestom.server.entity.metadata.minecart.AbstractMinecartMeta ?:
          return).setShakingDirection(arg0)
    }
  }

  @Serializable
  @SerialName("AbstractMinecartMeta_setShakingMultiplier")
  public data class ShakingMultiplier(
    public val arg0: Float
  ) : MobMeta() {
    public override fun apply(entity: Entity): Unit {
      (entity as? net.minestom.server.entity.metadata.minecart.AbstractMinecartMeta ?:
          return).setShakingMultiplier(arg0)
    }
  }

  @Serializable
  @SerialName("AbstractMinecartMeta_setCustomBlockIdAndDamage")
  public data class CustomBlockIdAndDamage(
    public val arg0: Int
  ) : MobMeta() {
    public override fun apply(entity: Entity): Unit {
      (entity as? net.minestom.server.entity.metadata.minecart.AbstractMinecartMeta ?:
          return).setCustomBlockIdAndDamage(arg0)
    }
  }

  @Serializable
  @SerialName("AbstractMinecartMeta_setCustomBlockYPosition")
  public data class CustomBlockYPosition(
    public val arg0: Int
  ) : MobMeta() {
    public override fun apply(entity: Entity): Unit {
      (entity as? net.minestom.server.entity.metadata.minecart.AbstractMinecartMeta ?:
          return).setCustomBlockYPosition(arg0)
    }
  }
}
