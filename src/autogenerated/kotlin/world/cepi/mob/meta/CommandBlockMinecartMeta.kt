package world.cepi.mob.meta

import java.lang.String
import kotlin.Unit
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import net.kyori.adventure.text.Component
import net.minestom.server.entity.Entity
import net.minestom.server.entity.metadata.MobMeta

@Serializable
public object CommandBlockMinecartMeta {
  @Serializable
  @SerialName("CommandBlockMinecartMeta_setCommand")
  public data class Command(
    public val arg0: String
  ) : MobMeta() {
    public override fun apply(entity: Entity): Unit {
      (entity as? net.minestom.server.entity.metadata.minecart.CommandBlockMinecartMeta ?:
          return).setCommand(arg0)
    }
  }

  @Serializable
  @SerialName("CommandBlockMinecartMeta_setLastOutput")
  public data class LastOutput(
    public val arg0: Component
  ) : MobMeta() {
    public override fun apply(entity: Entity): Unit {
      (entity as? net.minestom.server.entity.metadata.minecart.CommandBlockMinecartMeta ?:
          return).setLastOutput(arg0)
    }
  }
}
