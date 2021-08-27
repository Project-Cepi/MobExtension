package world.cepi.mob.meta

import kotlin.Unit
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import net.minestom.server.entity.Entity
import net.minestom.server.entity.metadata.MobMeta

public object VillagerMeta {
  @Serializable
  @SerialName("VillagerMeta_setVillagerData")
  public data class VillagerData(
    arg0: net.minestom.server.entity.metadata.villager.VillagerMeta.VillagerData
  ) : MobMeta() {
    public override fun apply(entity: Entity): Unit {
      (entity as? net.minestom.server.entity.metadata.villager.VillagerMeta ?:
          return).setVillagerData(arg0)
    }
  }
}
