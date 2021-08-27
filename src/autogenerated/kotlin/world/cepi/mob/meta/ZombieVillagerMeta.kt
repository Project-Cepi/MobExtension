package world.cepi.mob.meta

import kotlin.Boolean
import kotlin.Unit
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import net.minestom.server.entity.Entity
import net.minestom.server.entity.metadata.MobMeta
import net.minestom.server.entity.metadata.villager.VillagerMeta

@Serializable
public object ZombieVillagerMeta {
  @Serializable
  @SerialName("ZombieVillagerMeta_setVillagerData")
  public data class VillagerData(
    public val arg0: VillagerMeta.VillagerData
  ) : MobMeta() {
    public override fun apply(entity: Entity): Unit {
      (entity as? net.minestom.server.entity.metadata.monster.zombie.ZombieVillagerMeta ?:
          return).setVillagerData(arg0)
    }
  }

  @Serializable
  @SerialName("ZombieVillagerMeta_setConverting")
  public data class Converting(
    public val arg0: Boolean
  ) : MobMeta() {
    public override fun apply(entity: Entity): Unit {
      (entity as? net.minestom.server.entity.metadata.monster.zombie.ZombieVillagerMeta ?:
          return).setConverting(arg0)
    }
  }
}
