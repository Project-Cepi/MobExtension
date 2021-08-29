package world.cepi.mob.meta.generated

import kotlin.Boolean
import kotlin.Unit
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import net.minestom.server.entity.Entity
import net.minestom.server.entity.metadata.monster.ZoglinMeta
import world.cepi.mob.meta.MobMeta

@Serializable
public object MetaZoglin {
  @Serializable
  @SerialName("MetaZoglin_setBaby")
  public data class Baby(
    public val arg0: Boolean
  ) : MobMeta() {
    public override fun apply(entity: Entity): Unit {
      (entity.entityMeta as? ZoglinMeta ?: return).setBaby(arg0)
    }
  }
}
