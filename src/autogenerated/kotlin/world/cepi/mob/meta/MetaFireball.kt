package world.cepi.mob.meta

import kotlin.Unit
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import net.minestom.server.entity.Entity
import net.minestom.server.entity.metadata.item.FireballMeta
import net.minestom.server.item.ItemStack
import world.cepi.kstom.serializer.ItemStackSerializer

@Serializable
public object MetaFireball {
  @Serializable
  @SerialName("MetaFireball_setItem")
  public data class Item(
    @Serializable(ItemStackSerializer::class)
    public val arg0: ItemStack
  ) : MobMeta() {
    public override fun apply(entity: Entity): Unit {
      (entity.entityMeta as? FireballMeta ?: return).setItem(arg0)
    }
  }
}
