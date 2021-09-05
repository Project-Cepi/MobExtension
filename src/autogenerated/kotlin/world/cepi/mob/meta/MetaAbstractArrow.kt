package world.cepi.mob.meta

import kotlin.Boolean
import kotlin.Byte
import kotlin.Unit
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import net.minestom.server.entity.Entity
import net.minestom.server.entity.metadata.arrow.AbstractArrowMeta

@Serializable
public object MetaAbstractArrow {
  @Serializable
  @SerialName("MetaAbstractArrow_setCritical")
  public data class Critical(
    public val arg0: Boolean
  ) : MobMeta() {
    public override fun apply(entity: Entity): Unit {
      (entity.entityMeta as? AbstractArrowMeta ?: return).setCritical(arg0)
    }
  }

  @Serializable
  @SerialName("MetaAbstractArrow_setNoClip")
  public data class NoClip(
    public val arg0: Boolean
  ) : MobMeta() {
    public override fun apply(entity: Entity): Unit {
      (entity.entityMeta as? AbstractArrowMeta ?: return).setNoClip(arg0)
    }
  }

  @Serializable
  @SerialName("MetaAbstractArrow_setPiercingLevel")
  public data class PiercingLevel(
    public val arg0: Byte
  ) : MobMeta() {
    public override fun apply(entity: Entity): Unit {
      (entity.entityMeta as? AbstractArrowMeta ?: return).setPiercingLevel(arg0)
    }
  }
}
