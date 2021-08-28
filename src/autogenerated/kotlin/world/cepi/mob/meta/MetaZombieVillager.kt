package world.cepi.mob.meta

import kotlin.Boolean
import kotlin.Unit
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import net.minestom.server.entity.Entity
import net.minestom.server.entity.metadata.monster.zombie.ZombieVillagerMeta
import net.minestom.server.entity.metadata.villager.VillagerMeta

@Serializable
public object MetaZombieVillager {
  @Serializable
  @SerialName("MetaZombieVillager_setVillagerData")
  public data class VillagerData(
    public val arg0: VillagerMeta.VillagerData
  ) : MobMeta() {
    public override fun apply(entity: Entity): Unit {
      (entity as? ZombieVillagerMeta ?: return).setVillagerData(arg0)
    }
  }

  @Serializable
  @SerialName("MetaZombieVillager_setConverting")
  public data class Converting(
    public val arg0: Boolean
  ) : MobMeta() {
    public override fun apply(entity: Entity): Unit {
      (entity as? ZombieVillagerMeta ?: return).setConverting(arg0)
    }
  }
}
