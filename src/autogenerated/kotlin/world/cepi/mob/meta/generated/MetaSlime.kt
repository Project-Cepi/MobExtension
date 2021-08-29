package world.cepi.mob.meta.generated

import kotlin.Int
import kotlin.Unit
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import net.minestom.server.entity.Entity
import net.minestom.server.entity.metadata.other.SlimeMeta
import world.cepi.mob.meta.MobMeta

@Serializable
public object MetaSlime {
  @Serializable
  @SerialName("MetaSlime_setSize")
  public data class Size(
    public val arg0: Int
  ) : MobMeta() {
    public override fun apply(entity: Entity): Unit {
      (entity.entityMeta as? SlimeMeta ?: return).setSize(arg0)
    }
  }
}
