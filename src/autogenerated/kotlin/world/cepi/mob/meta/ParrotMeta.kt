package world.cepi.mob.meta

import kotlin.Unit
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import net.minestom.server.entity.Entity
import net.minestom.server.entity.metadata.MobMeta

public object ParrotMeta {
  @Serializable
  @SerialName("ParrotMeta_setColor")
  public data class Color(
    arg0: net.minestom.server.entity.metadata.animal.tameable.ParrotMeta.Color
  ) : MobMeta() {
    public override fun apply(entity: Entity): Unit {
      (entity as? net.minestom.server.entity.metadata.animal.tameable.ParrotMeta ?:
          return).setColor(arg0)
    }
  }
}
