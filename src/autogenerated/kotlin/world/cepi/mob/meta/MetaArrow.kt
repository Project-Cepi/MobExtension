package world.cepi.mob.meta

import kotlin.Int
import kotlin.Unit
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import net.minestom.server.entity.Entity
import net.minestom.server.entity.metadata.arrow.ArrowMeta

@Serializable
public object MetaArrow {
  @Serializable
  @SerialName("MetaArrow_setColor")
  public data class Color(
    public val arg0: Int
  ) : MobMeta() {
    public override fun apply(entity: Entity): Unit {
      (entity.entityMeta as? ArrowMeta ?: return).setColor(arg0)
    }
  }
}
