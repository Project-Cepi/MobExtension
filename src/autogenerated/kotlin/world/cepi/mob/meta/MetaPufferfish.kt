package world.cepi.mob.meta

import kotlin.Unit
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import net.minestom.server.entity.Entity
import net.minestom.server.entity.metadata.water.fish.PufferfishMeta

@Serializable
public object MetaPufferfish {
  @Serializable
  @SerialName("MetaPufferfish_setState")
  public data class State(
    public val arg0: PufferfishMeta.State
  ) : MobMeta() {
    public override fun apply(entity: Entity): Unit {
      (entity.entityMeta as? PufferfishMeta ?: return).setState(arg0)
    }
  }
}
