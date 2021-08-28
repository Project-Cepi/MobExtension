package world.cepi.mob.meta

import kotlin.Boolean
import kotlin.Unit
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import net.minestom.server.coordinate.Point
import net.minestom.server.entity.Entity
import net.minestom.server.entity.metadata.other.EndCrystalMeta

@Serializable
public object MetaEndCrystal {
  @Serializable
  @SerialName("MetaEndCrystal_setBeamTarget")
  public data class BeamTarget(
    public val arg0: Point
  ) : MobMeta() {
    public override fun apply(entity: Entity): Unit {
      (entity as? EndCrystalMeta ?: return).setBeamTarget(arg0)
    }
  }

  @Serializable
  @SerialName("MetaEndCrystal_setShowingBottom")
  public data class ShowingBottom(
    public val arg0: Boolean
  ) : MobMeta() {
    public override fun apply(entity: Entity): Unit {
      (entity as? EndCrystalMeta ?: return).setShowingBottom(arg0)
    }
  }
}
