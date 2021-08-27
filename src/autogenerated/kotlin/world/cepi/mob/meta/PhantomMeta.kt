package world.cepi.mob.meta

import kotlin.Int
import kotlin.Unit
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import net.minestom.server.entity.Entity
import net.minestom.server.entity.metadata.MobMeta

@Serializable
public object PhantomMeta {
  @Serializable
  @SerialName("PhantomMeta_setSize")
  public data class Size(
    public val arg0: Int
  ) : MobMeta() {
    public override fun apply(entity: Entity): Unit {
      (entity as? net.minestom.server.entity.metadata.flying.PhantomMeta ?: return).setSize(arg0)
    }
  }
}
