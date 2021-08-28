package world.cepi.mob.meta

import kotlin.Boolean
import kotlin.Unit
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import net.minestom.server.entity.Entity
import net.minestom.server.entity.metadata.minecart.FurnaceMinecartMeta

@Serializable
public object MetaFurnaceMinecart {
  @Serializable
  @SerialName("MetaFurnaceMinecart_setHasFuel")
  public data class HasFuel(
    public val arg0: Boolean
  ) : MobMeta() {
    public override fun apply(entity: Entity): Unit {
      (entity.entityMeta as? FurnaceMinecartMeta ?: return).setHasFuel(arg0)
    }
  }
}
