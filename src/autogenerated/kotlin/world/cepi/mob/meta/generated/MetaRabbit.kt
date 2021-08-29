package world.cepi.mob.meta.generated

import kotlin.Unit
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import net.minestom.server.entity.Entity
import net.minestom.server.entity.metadata.animal.RabbitMeta
import world.cepi.mob.meta.MobMeta

@Serializable
public object MetaRabbit {
  @Serializable
  @SerialName("MetaRabbit_setType")
  public data class Type(
    public val arg0: RabbitMeta.Type
  ) : MobMeta() {
    public override fun apply(entity: Entity): Unit {
      (entity.entityMeta as? RabbitMeta ?: return).setType(arg0)
    }
  }
}
