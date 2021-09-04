package world.cepi.mob.meta

import kotlin.Boolean
import kotlin.Unit
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import net.minestom.server.entity.Entity
import net.minestom.server.entity.metadata.monster.raider.WitchMeta

@Serializable
public object MetaWitch {
  @Serializable
  @SerialName("MetaWitch_setDrinkingPotion")
  public data class DrinkingPotion(
    public val arg0: Boolean
  ) : MobMeta() {
    public override fun apply(entity: Entity): Unit {
      (entity.entityMeta as? WitchMeta ?: return).setDrinkingPotion(arg0)
    }
  }
}
