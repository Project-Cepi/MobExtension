package world.cepi.mob.meta

import kotlin.Boolean
import kotlin.Unit
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import net.minestom.server.entity.Entity
import net.minestom.server.entity.metadata.MobMeta

public object ChestedHorseMeta {
  @Serializable
  @SerialName("ChestedHorseMeta_setHasChest")
  public data class HasChest(
    arg0: Boolean
  ) : MobMeta() {
    public override fun apply(entity: Entity): Unit {
      (entity as? net.minestom.server.entity.metadata.animal.ChestedHorseMeta ?:
          return).setHasChest(arg0)
    }
  }
}
