package world.cepi.mob.meta

import java.lang.String
import kotlin.Unit
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import net.kyori.adventure.text.Component
import net.minestom.server.entity.Entity
import net.minestom.server.entity.metadata.minecart.CommandBlockMinecartMeta

@Serializable
public object MetaCommandBlockMinecart {
  @Serializable
  @SerialName("MetaCommandBlockMinecart_setLastOutput")
  public data class LastOutput(
    public val arg0: Component
  ) : MobMeta() {
    public override fun apply(entity: Entity): Unit {
      (entity as? CommandBlockMinecartMeta ?: return).setLastOutput(arg0)
    }
  }

  @Serializable
  @SerialName("MetaCommandBlockMinecart_setCommand")
  public data class Command(
    public val arg0: String
  ) : MobMeta() {
    public override fun apply(entity: Entity): Unit {
      (entity as? CommandBlockMinecartMeta ?: return).setCommand(arg0)
    }
  }
}
