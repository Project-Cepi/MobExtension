package world.cepi.mob.meta

import java.util.UUID
import kotlin.Boolean
import kotlin.Unit
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import net.minestom.server.entity.Entity
import net.minestom.server.entity.metadata.MobMeta

public object AbstractHorseMeta {
  @Serializable
  @SerialName("AbstractHorseMeta_setOwner")
  public data class Owner(
    arg0: UUID
  ) : MobMeta() {
    public override fun apply(entity: Entity): Unit {
      (entity as? net.minestom.server.entity.metadata.animal.AbstractHorseMeta ?:
          return).setOwner(arg0)
    }
  }

  @Serializable
  @SerialName("AbstractHorseMeta_setRearing")
  public data class Rearing(
    arg0: Boolean
  ) : MobMeta() {
    public override fun apply(entity: Entity): Unit {
      (entity as? net.minestom.server.entity.metadata.animal.AbstractHorseMeta ?:
          return).setRearing(arg0)
    }
  }

  @Serializable
  @SerialName("AbstractHorseMeta_setMouthOpen")
  public data class MouthOpen(
    arg0: Boolean
  ) : MobMeta() {
    public override fun apply(entity: Entity): Unit {
      (entity as? net.minestom.server.entity.metadata.animal.AbstractHorseMeta ?:
          return).setMouthOpen(arg0)
    }
  }

  @Serializable
  @SerialName("AbstractHorseMeta_setTamed")
  public data class Tamed(
    arg0: Boolean
  ) : MobMeta() {
    public override fun apply(entity: Entity): Unit {
      (entity as? net.minestom.server.entity.metadata.animal.AbstractHorseMeta ?:
          return).setTamed(arg0)
    }
  }

  @Serializable
  @SerialName("AbstractHorseMeta_setSaddled")
  public data class Saddled(
    arg0: Boolean
  ) : MobMeta() {
    public override fun apply(entity: Entity): Unit {
      (entity as? net.minestom.server.entity.metadata.animal.AbstractHorseMeta ?:
          return).setSaddled(arg0)
    }
  }

  @Serializable
  @SerialName("AbstractHorseMeta_setHasBred")
  public data class HasBred(
    arg0: Boolean
  ) : MobMeta() {
    public override fun apply(entity: Entity): Unit {
      (entity as? net.minestom.server.entity.metadata.animal.AbstractHorseMeta ?:
          return).setHasBred(arg0)
    }
  }

  @Serializable
  @SerialName("AbstractHorseMeta_setEating")
  public data class Eating(
    arg0: Boolean
  ) : MobMeta() {
    public override fun apply(entity: Entity): Unit {
      (entity as? net.minestom.server.entity.metadata.animal.AbstractHorseMeta ?:
          return).setEating(arg0)
    }
  }
}
