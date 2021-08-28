package world.cepi.mob.meta

import kotlin.Unit
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import net.minestom.server.entity.Entity
import net.minestom.server.entity.metadata.villager.VillagerMeta

@Serializable
public object MetaVillager {
  @Serializable
  @SerialName("MetaVillager_setVillagerData")
  public data class VillagerData(
    public val arg0: VillagerMeta.VillagerData
  ) : MobMeta() {
    public override fun apply(entity: Entity): Unit {
      (entity as? VillagerMeta ?: return).setVillagerData(arg0)
    }
  }
}
