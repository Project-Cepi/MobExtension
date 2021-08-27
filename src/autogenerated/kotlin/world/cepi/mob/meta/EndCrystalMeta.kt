package world.cepi.mob.meta

import kotlin.Boolean
import kotlin.Unit
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import net.minestom.server.coordinate.Point
import net.minestom.server.entity.Entity
import net.minestom.server.entity.metadata.MobMeta

public object EndCrystalMeta {
  @Serializable
  @SerialName("EndCrystalMeta_setBeamTarget")
  public data class BeamTarget(
    arg0: Point
  ) : MobMeta() {
    public override fun apply(entity: Entity): Unit {
      (entity as? net.minestom.server.entity.metadata.other.EndCrystalMeta ?:
          return).setBeamTarget(arg0)
    }
  }

  @Serializable
  @SerialName("EndCrystalMeta_setShowingBottom")
  public data class ShowingBottom(
    arg0: Boolean
  ) : MobMeta() {
    public override fun apply(entity: Entity): Unit {
      (entity as? net.minestom.server.entity.metadata.other.EndCrystalMeta ?:
          return).setShowingBottom(arg0)
    }
  }
}
