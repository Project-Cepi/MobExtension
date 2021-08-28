package world.cepi.mob.meta

import kotlin.Boolean
import kotlin.Byte
import kotlin.Unit
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import net.minestom.server.entity.Entity
import net.minestom.server.entity.metadata.animal.SheepMeta

@Serializable
public object MetaSheep {
  @Serializable
  @SerialName("MetaSheep_setColor")
  public data class Color(
    public val arg0: Byte
  ) : MobMeta() {
    public override fun apply(entity: Entity): Unit {
      (entity as? SheepMeta ?: return).setColor(arg0)
    }
  }

  @Serializable
  @SerialName("MetaSheep_setSheared")
  public data class Sheared(
    public val arg0: Boolean
  ) : MobMeta() {
    public override fun apply(entity: Entity): Unit {
      (entity as? SheepMeta ?: return).setSheared(arg0)
    }
  }
}
