package world.cepi.mob.meta

import kotlin.Int
import kotlin.Unit
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import net.minestom.server.entity.Entity
import net.minestom.server.entity.metadata.flying.PhantomMeta

@Serializable
public object MetaPhantom {
  @Serializable
  @SerialName("MetaPhantom_setSize")
  public data class Size(
    public val arg0: Int
  ) : MobMeta() {
    public override fun apply(entity: Entity): Unit {
      (entity as? PhantomMeta ?: return).setSize(arg0)
    }
  }
}
