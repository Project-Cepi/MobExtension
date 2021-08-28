package world.cepi.mob.meta

import kotlin.Boolean
import kotlin.Unit
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import net.minestom.server.entity.Entity
import net.minestom.server.entity.metadata.monster.zombie.ZombieMeta

@Serializable
public object MetaZombie {
  @Serializable
  @SerialName("MetaZombie_setBaby")
  public data class Baby(
    public val arg0: Boolean
  ) : MobMeta() {
    public override fun apply(entity: Entity): Unit {
      (entity.entityMeta as? ZombieMeta ?: return).setBaby(arg0)
    }
  }

  @Serializable
  @SerialName("MetaZombie_setBecomingDrowned")
  public data class BecomingDrowned(
    public val arg0: Boolean
  ) : MobMeta() {
    public override fun apply(entity: Entity): Unit {
      (entity.entityMeta as? ZombieMeta ?: return).setBecomingDrowned(arg0)
    }
  }
}
