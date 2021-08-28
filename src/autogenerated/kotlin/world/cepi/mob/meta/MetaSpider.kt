package world.cepi.mob.meta

import kotlin.Boolean
import kotlin.Unit
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import net.minestom.server.entity.Entity
import net.minestom.server.entity.metadata.monster.SpiderMeta

@Serializable
public object MetaSpider {
  @Serializable
  @SerialName("MetaSpider_setClimbing")
  public data class Climbing(
    public val arg0: Boolean
  ) : MobMeta() {
    public override fun apply(entity: Entity): Unit {
      (entity.entityMeta as? SpiderMeta ?: return).setClimbing(arg0)
    }
  }
}
