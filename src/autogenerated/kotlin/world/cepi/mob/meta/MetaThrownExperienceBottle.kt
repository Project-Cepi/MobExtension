package world.cepi.mob.meta

import kotlin.Unit
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import net.minestom.server.entity.Entity
import net.minestom.server.entity.metadata.item.ThrownExperienceBottleMeta
import net.minestom.server.item.ItemStack

@Serializable
public object MetaThrownExperienceBottle {
  @Serializable
  @SerialName("MetaThrownExperienceBottle_setItem")
  public data class Item(
    public val arg0: ItemStack
  ) : MobMeta() {
    public override fun apply(entity: Entity): Unit {
      (entity as? ThrownExperienceBottleMeta ?: return).setItem(arg0)
    }
  }
}
