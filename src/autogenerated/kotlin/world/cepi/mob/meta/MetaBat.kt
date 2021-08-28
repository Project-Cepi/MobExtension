package world.cepi.mob.meta

import kotlin.Boolean
import kotlin.Unit
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import net.minestom.server.entity.Entity
import net.minestom.server.entity.metadata.ambient.BatMeta

@Serializable
public object MetaBat {
  @Serializable
  @SerialName("MetaBat_setHanging")
  public data class Hanging(
    public val arg0: Boolean
  ) : MobMeta() {
    public override fun apply(entity: Entity): Unit {
      (entity as? BatMeta ?: return).setHanging(arg0)
    }
  }
}
