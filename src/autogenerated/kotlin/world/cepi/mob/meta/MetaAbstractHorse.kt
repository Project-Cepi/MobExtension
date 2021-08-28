package world.cepi.mob.meta

import kotlin.Boolean
import kotlin.Unit
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import net.minestom.server.entity.Entity
import net.minestom.server.entity.metadata.animal.AbstractHorseMeta

@Serializable
public object MetaAbstractHorse {
  @Serializable
  @SerialName("MetaAbstractHorse_setMouthOpen")
  public data class MouthOpen(
    public val arg0: Boolean
  ) : MobMeta() {
    public override fun apply(entity: Entity): Unit {
      (entity.entityMeta as? AbstractHorseMeta ?: return).setMouthOpen(arg0)
    }
  }

  @Serializable
  @SerialName("MetaAbstractHorse_setTamed")
  public data class Tamed(
    public val arg0: Boolean
  ) : MobMeta() {
    public override fun apply(entity: Entity): Unit {
      (entity.entityMeta as? AbstractHorseMeta ?: return).setTamed(arg0)
    }
  }

  @Serializable
  @SerialName("MetaAbstractHorse_setSaddled")
  public data class Saddled(
    public val arg0: Boolean
  ) : MobMeta() {
    public override fun apply(entity: Entity): Unit {
      (entity.entityMeta as? AbstractHorseMeta ?: return).setSaddled(arg0)
    }
  }

  @Serializable
  @SerialName("MetaAbstractHorse_setHasBred")
  public data class HasBred(
    public val arg0: Boolean
  ) : MobMeta() {
    public override fun apply(entity: Entity): Unit {
      (entity.entityMeta as? AbstractHorseMeta ?: return).setHasBred(arg0)
    }
  }

  @Serializable
  @SerialName("MetaAbstractHorse_setEating")
  public data class Eating(
    public val arg0: Boolean
  ) : MobMeta() {
    public override fun apply(entity: Entity): Unit {
      (entity.entityMeta as? AbstractHorseMeta ?: return).setEating(arg0)
    }
  }

  @Serializable
  @SerialName("MetaAbstractHorse_setRearing")
  public data class Rearing(
    public val arg0: Boolean
  ) : MobMeta() {
    public override fun apply(entity: Entity): Unit {
      (entity.entityMeta as? AbstractHorseMeta ?: return).setRearing(arg0)
    }
  }
}
