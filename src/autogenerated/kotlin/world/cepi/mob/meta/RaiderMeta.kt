package world.cepi.mob.meta

import kotlin.Boolean
import kotlin.Unit
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import net.minestom.server.entity.Entity
import net.minestom.server.entity.metadata.MobMeta

@Serializable
public object RaiderMeta {
  @Serializable
  @SerialName("RaiderMeta_setCelebrating")
  public data class Celebrating(
    public val arg0: Boolean
  ) : MobMeta() {
    public override fun apply(entity: Entity): Unit {
      (entity as? net.minestom.server.entity.metadata.monster.raider.RaiderMeta ?:
          return).setCelebrating(arg0)
    }
  }
}
