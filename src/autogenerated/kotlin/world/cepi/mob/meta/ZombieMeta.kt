package world.cepi.mob.meta

import kotlin.Boolean
import kotlin.Unit
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import net.minestom.server.entity.Entity
import net.minestom.server.entity.metadata.MobMeta

public object ZombieMeta {
  @Serializable
  @SerialName("ZombieMeta_setBaby")
  public data class Baby(
    arg0: Boolean
  ) : MobMeta() {
    public override fun apply(entity: Entity): Unit {
      (entity as? net.minestom.server.entity.metadata.monster.zombie.ZombieMeta ?:
          return).setBaby(arg0)
    }
  }

  @Serializable
  @SerialName("ZombieMeta_setBecomingDrowned")
  public data class BecomingDrowned(
    arg0: Boolean
  ) : MobMeta() {
    public override fun apply(entity: Entity): Unit {
      (entity as? net.minestom.server.entity.metadata.monster.zombie.ZombieMeta ?:
          return).setBecomingDrowned(arg0)
    }
  }
}
