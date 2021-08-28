package world.cepi.mob.meta

import kotlin.Boolean
import kotlin.Unit
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import net.minestom.server.entity.Entity
import net.minestom.server.entity.metadata.animal.tameable.TameableAnimalMeta

@Serializable
public object MetaTameableAnimal {
  @Serializable
  @SerialName("MetaTameableAnimal_setSitting")
  public data class Sitting(
    public val arg0: Boolean
  ) : MobMeta() {
    public override fun apply(entity: Entity): Unit {
      (entity.entityMeta as? TameableAnimalMeta ?: return).setSitting(arg0)
    }
  }

  @Serializable
  @SerialName("MetaTameableAnimal_setTamed")
  public data class Tamed(
    public val arg0: Boolean
  ) : MobMeta() {
    public override fun apply(entity: Entity): Unit {
      (entity.entityMeta as? TameableAnimalMeta ?: return).setTamed(arg0)
    }
  }
}
