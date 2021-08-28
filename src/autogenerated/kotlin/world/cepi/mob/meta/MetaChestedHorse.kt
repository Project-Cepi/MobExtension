package world.cepi.mob.meta

import kotlin.Boolean
import kotlin.Unit
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import net.minestom.server.entity.Entity
import net.minestom.server.entity.metadata.animal.ChestedHorseMeta

@Serializable
public object MetaChestedHorse {
  @Serializable
  @SerialName("MetaChestedHorse_setHasChest")
  public data class HasChest(
    public val arg0: Boolean
  ) : MobMeta() {
    public override fun apply(entity: Entity): Unit {
      (entity as? ChestedHorseMeta ?: return).setHasChest(arg0)
    }
  }
}
