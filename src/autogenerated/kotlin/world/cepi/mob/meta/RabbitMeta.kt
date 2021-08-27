package world.cepi.mob.meta

import kotlin.Unit
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import net.minestom.server.entity.Entity
import net.minestom.server.entity.metadata.MobMeta

public object RabbitMeta {
  @Serializable
  @SerialName("RabbitMeta_setType")
  public data class Type(
    arg0: net.minestom.server.entity.metadata.animal.RabbitMeta.Type
  ) : MobMeta() {
    public override fun apply(entity: Entity): Unit {
      (entity as? net.minestom.server.entity.metadata.animal.RabbitMeta ?: return).setType(arg0)
    }
  }
}
