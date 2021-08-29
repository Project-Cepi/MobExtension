package world.cepi.mob.meta.generated

import kotlin.Boolean
import kotlin.Unit
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import net.minestom.server.entity.Entity
import net.minestom.server.entity.metadata.AgeableMobMeta
import world.cepi.mob.meta.MobMeta

@Serializable
public object MetaAgeableMob {
  @Serializable
  @SerialName("MetaAgeableMob_setBaby")
  public data class Baby(
    public val arg0: Boolean
  ) : MobMeta() {
    public override fun apply(entity: Entity): Unit {
      (entity.entityMeta as? AgeableMobMeta ?: return).setBaby(arg0)
    }
  }
}
