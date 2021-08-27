package world.cepi.mob.meta

import java.lang.Integer
import kotlin.Boolean
import kotlin.Unit
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import net.minestom.server.entity.Entity
import net.minestom.server.entity.metadata.MobMeta

public object EndermanMeta {
  @Serializable
  @SerialName("EndermanMeta_setCarriedBlockID")
  public data class CarriedBlockID(
    arg0: Integer
  ) : MobMeta() {
    public override fun apply(entity: Entity): Unit {
      (entity as? net.minestom.server.entity.metadata.monster.EndermanMeta ?:
          return).setCarriedBlockID(arg0)
    }
  }

  @Serializable
  @SerialName("EndermanMeta_setScreaming")
  public data class Screaming(
    arg0: Boolean
  ) : MobMeta() {
    public override fun apply(entity: Entity): Unit {
      (entity as? net.minestom.server.entity.metadata.monster.EndermanMeta ?:
          return).setScreaming(arg0)
    }
  }

  @Serializable
  @SerialName("EndermanMeta_setStaring")
  public data class Staring(
    arg0: Boolean
  ) : MobMeta() {
    public override fun apply(entity: Entity): Unit {
      (entity as? net.minestom.server.entity.metadata.monster.EndermanMeta ?:
          return).setStaring(arg0)
    }
  }
}
