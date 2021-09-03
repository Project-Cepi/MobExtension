package world.cepi.mob.meta.generated

import kotlin.String
import kotlin.Unit
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import net.kyori.adventure.text.Component
import net.minestom.server.entity.Entity
import net.minestom.server.entity.metadata.minecart.CommandBlockMinecartMeta
import world.cepi.mob.meta.MobMeta

@Serializable
public object MetaCommandBlockMinecart {
  @Serializable
  @SerialName("MetaCommandBlockMinecart_setCommand")
  public data class Command(
    public val arg0: String
  ) : MobMeta() {
    public override fun apply(entity: Entity): Unit {
      (entity.entityMeta as? CommandBlockMinecartMeta ?: return).setCommand(arg0)
    }
  }

  @Serializable
  @SerialName("MetaCommandBlockMinecart_setLastOutput")
  public data class LastOutput(
    public val arg0: Component
  ) : MobMeta() {
    public override fun apply(entity: Entity): Unit {
      (entity.entityMeta as? CommandBlockMinecartMeta ?: return).setLastOutput(arg0)
    }
  }
}