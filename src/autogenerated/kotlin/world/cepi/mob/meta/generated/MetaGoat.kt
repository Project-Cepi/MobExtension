package world.cepi.mob.meta.generated

import kotlin.Boolean
import kotlin.Unit
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import net.minestom.server.entity.Entity
import net.minestom.server.entity.metadata.animal.GoatMeta
import world.cepi.mob.meta.MobMeta

@Serializable
public object MetaGoat {
  @Serializable
  @SerialName("MetaGoat_setScreaming")
  public data class Screaming(
    public val arg0: Boolean
  ) : MobMeta() {
    public override fun apply(entity: Entity): Unit {
      (entity.entityMeta as? GoatMeta ?: return).setScreaming(arg0)
    }
  }
}
