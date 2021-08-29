package world.cepi.mob.meta.generated

import kotlin.Unit
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import net.minestom.server.entity.Entity
import net.minestom.server.entity.metadata.item.ThrownEggMeta
import net.minestom.server.item.ItemStack
import world.cepi.kstom.serializer.ItemStackSerializer
import world.cepi.mob.meta.MobMeta

@Serializable
public object MetaThrownEgg {
  @Serializable
  @SerialName("MetaThrownEgg_setItem")
  public data class Item(
    @Serializable(ItemStackSerializer::class)
    public val arg0: ItemStack
  ) : MobMeta() {
    public override fun apply(entity: Entity): Unit {
      (entity.entityMeta as? ThrownEggMeta ?: return).setItem(arg0)
    }
  }
}
