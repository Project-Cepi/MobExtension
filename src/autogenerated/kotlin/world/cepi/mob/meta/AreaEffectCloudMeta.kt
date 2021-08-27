package world.cepi.mob.meta

import kotlin.Boolean
import kotlin.Float
import kotlin.Int
import kotlin.Unit
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import net.minestom.server.entity.Entity
import net.minestom.server.entity.metadata.MobMeta

@Serializable
public object AreaEffectCloudMeta {
  @Serializable
  @SerialName("AreaEffectCloudMeta_setColor")
  public data class Color(
    public val arg0: Int
  ) : MobMeta() {
    public override fun apply(entity: Entity): Unit {
      (entity as? net.minestom.server.entity.metadata.other.AreaEffectCloudMeta ?:
          return).setColor(arg0)
    }
  }

  @Serializable
  @SerialName("AreaEffectCloudMeta_setRadius")
  public data class Radius(
    public val arg0: Float
  ) : MobMeta() {
    public override fun apply(entity: Entity): Unit {
      (entity as? net.minestom.server.entity.metadata.other.AreaEffectCloudMeta ?:
          return).setRadius(arg0)
    }
  }

  @Serializable
  @SerialName("AreaEffectCloudMeta_setSinglePoint")
  public data class SinglePoint(
    public val arg0: Boolean
  ) : MobMeta() {
    public override fun apply(entity: Entity): Unit {
      (entity as? net.minestom.server.entity.metadata.other.AreaEffectCloudMeta ?:
          return).setSinglePoint(arg0)
    }
  }
}
