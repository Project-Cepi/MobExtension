package world.cepi.mob.meta

import kotlin.Boolean
import kotlin.Unit
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import net.minestom.server.entity.Entity
import net.minestom.server.entity.metadata.other.FireworkRocketMeta
import net.minestom.server.item.ItemStack
import world.cepi.kstom.serializer.ItemStackSerializer

@Serializable
public object MetaFireworkRocket {
  @Serializable
  @SerialName("MetaFireworkRocket_setFireworkInfo")
  public data class FireworkInfo(
    @Serializable(ItemStackSerializer::class)
    public val arg0: ItemStack
  ) : MobMeta() {
    public override fun apply(entity: Entity): Unit {
      (entity.entityMeta as? FireworkRocketMeta ?: return).setFireworkInfo(arg0)
    }
  }

  @Serializable
  @SerialName("MetaFireworkRocket_setShotAtAngle")
  public data class ShotAtAngle(
    public val arg0: Boolean
  ) : MobMeta() {
    public override fun apply(entity: Entity): Unit {
      (entity.entityMeta as? FireworkRocketMeta ?: return).setShotAtAngle(arg0)
    }
  }
}
