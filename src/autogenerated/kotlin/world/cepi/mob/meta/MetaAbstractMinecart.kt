package world.cepi.mob.meta

import kotlin.Float
import kotlin.Int
import kotlin.Unit
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import net.minestom.server.entity.Entity
import net.minestom.server.entity.metadata.minecart.AbstractMinecartMeta

@Serializable
public object MetaAbstractMinecart {
  @Serializable
  @SerialName("MetaAbstractMinecart_setShakingPower")
  public data class ShakingPower(
    public val arg0: Int
  ) : MobMeta() {
    public override fun apply(entity: Entity): Unit {
      (entity as? AbstractMinecartMeta ?: return).setShakingPower(arg0)
    }
  }

  @Serializable
  @SerialName("MetaAbstractMinecart_setShakingDirection")
  public data class ShakingDirection(
    public val arg0: Int
  ) : MobMeta() {
    public override fun apply(entity: Entity): Unit {
      (entity as? AbstractMinecartMeta ?: return).setShakingDirection(arg0)
    }
  }

  @Serializable
  @SerialName("MetaAbstractMinecart_setShakingMultiplier")
  public data class ShakingMultiplier(
    public val arg0: Float
  ) : MobMeta() {
    public override fun apply(entity: Entity): Unit {
      (entity as? AbstractMinecartMeta ?: return).setShakingMultiplier(arg0)
    }
  }

  @Serializable
  @SerialName("MetaAbstractMinecart_setCustomBlockIdAndDamage")
  public data class CustomBlockIdAndDamage(
    public val arg0: Int
  ) : MobMeta() {
    public override fun apply(entity: Entity): Unit {
      (entity as? AbstractMinecartMeta ?: return).setCustomBlockIdAndDamage(arg0)
    }
  }

  @Serializable
  @SerialName("MetaAbstractMinecart_setCustomBlockYPosition")
  public data class CustomBlockYPosition(
    public val arg0: Int
  ) : MobMeta() {
    public override fun apply(entity: Entity): Unit {
      (entity as? AbstractMinecartMeta ?: return).setCustomBlockYPosition(arg0)
    }
  }
}
