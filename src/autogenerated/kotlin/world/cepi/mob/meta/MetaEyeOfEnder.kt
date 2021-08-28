package world.cepi.mob.meta

import kotlin.Unit
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import net.minestom.server.entity.Entity
import net.minestom.server.entity.metadata.item.EyeOfEnderMeta
import net.minestom.server.item.ItemStack

@Serializable
public object MetaEyeOfEnder {
  @Serializable
  @SerialName("MetaEyeOfEnder_setItem")
  public data class Item(
    public val arg0: ItemStack
  ) : MobMeta() {
    public override fun apply(entity: Entity): Unit {
      (entity as? EyeOfEnderMeta ?: return).setItem(arg0)
    }
  }
}
