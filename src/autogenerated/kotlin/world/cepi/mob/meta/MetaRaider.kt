package world.cepi.mob.meta

import kotlin.Boolean
import kotlin.Unit
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import net.minestom.server.entity.Entity
import net.minestom.server.entity.metadata.monster.raider.RaiderMeta

@Serializable
public object MetaRaider {
  @Serializable
  @SerialName("MetaRaider_setCelebrating")
  public data class Celebrating(
    public val arg0: Boolean
  ) : MobMeta() {
    public override fun apply(entity: Entity): Unit {
      (entity.entityMeta as? RaiderMeta ?: return).setCelebrating(arg0)
    }
  }
}
