package world.cepi.mob.meta

import java.util.UUID
import kotlin.Boolean
import kotlin.Unit
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import net.minestom.server.entity.Entity
import net.minestom.server.entity.metadata.MobMeta

@Serializable
public object TameableAnimalMeta {
  @Serializable
  @SerialName("TameableAnimalMeta_setOwner")
  public data class Owner(
    public val arg0: UUID
  ) : MobMeta() {
    public override fun apply(entity: Entity): Unit {
      (entity as? net.minestom.server.entity.metadata.animal.tameable.TameableAnimalMeta ?:
          return).setOwner(arg0)
    }
  }

  @Serializable
  @SerialName("TameableAnimalMeta_setSitting")
  public data class Sitting(
    public val arg0: Boolean
  ) : MobMeta() {
    public override fun apply(entity: Entity): Unit {
      (entity as? net.minestom.server.entity.metadata.animal.tameable.TameableAnimalMeta ?:
          return).setSitting(arg0)
    }
  }

  @Serializable
  @SerialName("TameableAnimalMeta_setTamed")
  public data class Tamed(
    public val arg0: Boolean
  ) : MobMeta() {
    public override fun apply(entity: Entity): Unit {
      (entity as? net.minestom.server.entity.metadata.animal.tameable.TameableAnimalMeta ?:
          return).setTamed(arg0)
    }
  }
}
