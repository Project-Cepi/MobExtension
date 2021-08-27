package world.cepi.mob.meta

import kotlin.Unit
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import net.minestom.server.entity.Entity
import net.minestom.server.entity.metadata.MobMeta

@Serializable
public object PufferfishMeta {
  @Serializable
  @SerialName("PufferfishMeta_setState")
  public data class State(
    public val arg0: net.minestom.server.entity.metadata.water.fish.PufferfishMeta.State
  ) : MobMeta() {
    public override fun apply(entity: Entity): Unit {
      (entity as? net.minestom.server.entity.metadata.water.fish.PufferfishMeta ?:
          return).setState(arg0)
    }
  }

  @Serializable
  @SerialName("PufferfishMeta_updateBoundingBox")
  public data class AteBoundingBox(
    public val arg0: net.minestom.server.entity.metadata.water.fish.PufferfishMeta.State
  ) : MobMeta() {
    public override fun apply(entity: Entity): Unit {
      (entity as? net.minestom.server.entity.metadata.water.fish.PufferfishMeta ?:
          return).updateBoundingBox(arg0)
    }
  }
}
