package world.cepi.mob.meta.generated

import kotlin.Boolean
import kotlin.Unit
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import net.minestom.server.entity.Entity
import net.minestom.server.entity.metadata.animal.ChestedHorseMeta
import world.cepi.mob.meta.MobMeta

@Serializable
public object MetaChestedHorse {
  @Serializable
  @SerialName("MetaChestedHorse_setHasChest")
  public data class HasChest(
    public val arg0: Boolean
  ) : MobMeta() {
    public override fun apply(entity: Entity): Unit {
      (entity.entityMeta as? ChestedHorseMeta ?: return).setHasChest(arg0)
    }
  }
}
