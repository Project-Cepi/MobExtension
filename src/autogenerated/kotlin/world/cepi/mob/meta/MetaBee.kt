package world.cepi.mob.meta

import kotlin.Boolean
import kotlin.Int
import kotlin.Unit
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import net.minestom.server.entity.Entity
import net.minestom.server.entity.metadata.animal.BeeMeta

@Serializable
public object MetaBee {
  @Serializable
  @SerialName("MetaBee_setAngry")
  public data class Angry(
    public val arg0: Boolean
  ) : MobMeta() {
    public override fun apply(entity: Entity): Unit {
      (entity as? BeeMeta ?: return).setAngry(arg0)
    }
  }

  @Serializable
  @SerialName("MetaBee_setHasStung")
  public data class HasStung(
    public val arg0: Boolean
  ) : MobMeta() {
    public override fun apply(entity: Entity): Unit {
      (entity as? BeeMeta ?: return).setHasStung(arg0)
    }
  }

  @Serializable
  @SerialName("MetaBee_setHasNectar")
  public data class HasNectar(
    public val arg0: Boolean
  ) : MobMeta() {
    public override fun apply(entity: Entity): Unit {
      (entity as? BeeMeta ?: return).setHasNectar(arg0)
    }
  }

  @Serializable
  @SerialName("MetaBee_setAngerTicks")
  public data class AngerTicks(
    public val arg0: Int
  ) : MobMeta() {
    public override fun apply(entity: Entity): Unit {
      (entity as? BeeMeta ?: return).setAngerTicks(arg0)
    }
  }
}
