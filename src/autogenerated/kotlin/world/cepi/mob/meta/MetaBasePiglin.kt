package world.cepi.mob.meta

import kotlin.Boolean
import kotlin.Unit
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import net.minestom.server.entity.Entity
import net.minestom.server.entity.metadata.monster.BasePiglinMeta

@Serializable
public object MetaBasePiglin {
  @Serializable
  @SerialName("MetaBasePiglin_setImmuneToZombification")
  public data class ImmuneToZombification(
    public val arg0: Boolean
  ) : MobMeta() {
    public override fun apply(entity: Entity): Unit {
      (entity as? BasePiglinMeta ?: return).setImmuneToZombification(arg0)
    }
  }
}
