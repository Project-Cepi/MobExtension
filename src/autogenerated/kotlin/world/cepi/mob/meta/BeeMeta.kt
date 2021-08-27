package world.cepi.mob.meta

import kotlin.Boolean
import kotlin.Int
import kotlin.Unit
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import net.minestom.server.entity.Entity
import net.minestom.server.entity.metadata.MobMeta

public object BeeMeta {
  @Serializable
  @SerialName("BeeMeta_setAngry")
  public data class Angry(
    arg0: Boolean
  ) : MobMeta() {
    public override fun apply(entity: Entity): Unit {
      (entity as? net.minestom.server.entity.metadata.animal.BeeMeta ?: return).setAngry(arg0)
    }
  }

  @Serializable
  @SerialName("BeeMeta_setHasStung")
  public data class HasStung(
    arg0: Boolean
  ) : MobMeta() {
    public override fun apply(entity: Entity): Unit {
      (entity as? net.minestom.server.entity.metadata.animal.BeeMeta ?: return).setHasStung(arg0)
    }
  }

  @Serializable
  @SerialName("BeeMeta_setHasNectar")
  public data class HasNectar(
    arg0: Boolean
  ) : MobMeta() {
    public override fun apply(entity: Entity): Unit {
      (entity as? net.minestom.server.entity.metadata.animal.BeeMeta ?: return).setHasNectar(arg0)
    }
  }

  @Serializable
  @SerialName("BeeMeta_setAngerTicks")
  public data class AngerTicks(
    arg0: Int
  ) : MobMeta() {
    public override fun apply(entity: Entity): Unit {
      (entity as? net.minestom.server.entity.metadata.animal.BeeMeta ?: return).setAngerTicks(arg0)
    }
  }
}
