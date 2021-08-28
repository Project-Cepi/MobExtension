package world.cepi.mob.meta

import kotlin.Boolean
import kotlin.Int
import kotlin.Unit
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import net.minestom.server.entity.Entity
import net.minestom.server.entity.metadata.monster.EndermanMeta

@Serializable
public object MetaEnderman {
  @Serializable
  @SerialName("MetaEnderman_setCarriedBlockID")
  public data class CarriedBlockID(
    public val arg0: Int
  ) : MobMeta() {
    public override fun apply(entity: Entity): Unit {
      (entity as? EndermanMeta ?: return).setCarriedBlockID(arg0)
    }
  }

  @Serializable
  @SerialName("MetaEnderman_setScreaming")
  public data class Screaming(
    public val arg0: Boolean
  ) : MobMeta() {
    public override fun apply(entity: Entity): Unit {
      (entity as? EndermanMeta ?: return).setScreaming(arg0)
    }
  }

  @Serializable
  @SerialName("MetaEnderman_setStaring")
  public data class Staring(
    public val arg0: Boolean
  ) : MobMeta() {
    public override fun apply(entity: Entity): Unit {
      (entity as? EndermanMeta ?: return).setStaring(arg0)
    }
  }
}
