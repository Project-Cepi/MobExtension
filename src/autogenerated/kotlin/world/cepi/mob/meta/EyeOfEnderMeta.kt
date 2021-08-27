package world.cepi.mob.meta

import kotlin.Unit
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import net.minestom.server.entity.Entity
import net.minestom.server.entity.metadata.MobMeta
import net.minestom.server.item.ItemStack

public object EyeOfEnderMeta {
  @Serializable
  @SerialName("EyeOfEnderMeta_setItem")
  public data class Item(
    arg0: ItemStack
  ) : MobMeta() {
    public override fun apply(entity: Entity): Unit {
      (entity as? net.minestom.server.entity.metadata.item.EyeOfEnderMeta ?: return).setItem(arg0)
    }
  }
}
