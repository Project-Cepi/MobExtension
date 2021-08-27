package world.cepi.mob.meta

import kotlin.Boolean
import kotlin.Byte
import kotlin.Unit
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import net.minestom.server.entity.Entity
import net.minestom.server.entity.metadata.MobMeta

@Serializable
public object AbstractArrowMeta {
  @Serializable
  @SerialName("AbstractArrowMeta_setCritical")
  public data class Critical(
    public val arg0: Boolean
  ) : MobMeta() {
    public override fun apply(entity: Entity): Unit {
      (entity as? net.minestom.server.entity.metadata.arrow.AbstractArrowMeta ?:
          return).setCritical(arg0)
    }
  }

  @Serializable
  @SerialName("AbstractArrowMeta_setNoClip")
  public data class NoClip(
    public val arg0: Boolean
  ) : MobMeta() {
    public override fun apply(entity: Entity): Unit {
      (entity as? net.minestom.server.entity.metadata.arrow.AbstractArrowMeta ?:
          return).setNoClip(arg0)
    }
  }

  @Serializable
  @SerialName("AbstractArrowMeta_setPiercingLevel")
  public data class PiercingLevel(
    public val arg0: Byte
  ) : MobMeta() {
    public override fun apply(entity: Entity): Unit {
      (entity as? net.minestom.server.entity.metadata.arrow.AbstractArrowMeta ?:
          return).setPiercingLevel(arg0)
    }
  }
}
