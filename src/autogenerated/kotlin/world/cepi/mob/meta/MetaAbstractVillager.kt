package world.cepi.mob.meta

import kotlin.Int
import kotlin.Unit
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import net.minestom.server.entity.Entity
import net.minestom.server.entity.metadata.villager.AbstractVillagerMeta

@Serializable
public object MetaAbstractVillager {
  @Serializable
  @SerialName("MetaAbstractVillager_setHeadShakeTimer")
  public data class HeadShakeTimer(
    public val arg0: Int
  ) : MobMeta() {
    public override fun apply(entity: Entity): Unit {
      (entity as? AbstractVillagerMeta ?: return).setHeadShakeTimer(arg0)
    }
  }
}
