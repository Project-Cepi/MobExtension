package world.cepi.mob.meta

import java.util.UUID
import kotlin.Boolean
import kotlin.Unit
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import net.minestom.server.entity.Entity
import net.minestom.server.entity.metadata.MobMeta

@Serializable
public object FoxMeta {
  @Serializable
  @SerialName("FoxMeta_setType")
  public data class Type(
    public val arg0: net.minestom.server.entity.metadata.animal.FoxMeta.Type
  ) : MobMeta() {
    public override fun apply(entity: Entity): Unit {
      (entity as? net.minestom.server.entity.metadata.animal.FoxMeta ?: return).setType(arg0)
    }
  }

  @Serializable
  @SerialName("FoxMeta_setFoxSneaking")
  public data class FoxSneaking(
    public val arg0: Boolean
  ) : MobMeta() {
    public override fun apply(entity: Entity): Unit {
      (entity as? net.minestom.server.entity.metadata.animal.FoxMeta ?: return).setFoxSneaking(arg0)
    }
  }

  @Serializable
  @SerialName("FoxMeta_setInterested")
  public data class Interested(
    public val arg0: Boolean
  ) : MobMeta() {
    public override fun apply(entity: Entity): Unit {
      (entity as? net.minestom.server.entity.metadata.animal.FoxMeta ?: return).setInterested(arg0)
    }
  }

  @Serializable
  @SerialName("FoxMeta_setPouncing")
  public data class Pouncing(
    public val arg0: Boolean
  ) : MobMeta() {
    public override fun apply(entity: Entity): Unit {
      (entity as? net.minestom.server.entity.metadata.animal.FoxMeta ?: return).setPouncing(arg0)
    }
  }

  @Serializable
  @SerialName("FoxMeta_setSleeping")
  public data class Sleeping(
    public val arg0: Boolean
  ) : MobMeta() {
    public override fun apply(entity: Entity): Unit {
      (entity as? net.minestom.server.entity.metadata.animal.FoxMeta ?: return).setSleeping(arg0)
    }
  }

  @Serializable
  @SerialName("FoxMeta_setFaceplanted")
  public data class Faceplanted(
    public val arg0: Boolean
  ) : MobMeta() {
    public override fun apply(entity: Entity): Unit {
      (entity as? net.minestom.server.entity.metadata.animal.FoxMeta ?: return).setFaceplanted(arg0)
    }
  }

  @Serializable
  @SerialName("FoxMeta_setDefending")
  public data class Defending(
    public val arg0: Boolean
  ) : MobMeta() {
    public override fun apply(entity: Entity): Unit {
      (entity as? net.minestom.server.entity.metadata.animal.FoxMeta ?: return).setDefending(arg0)
    }
  }

  @Serializable
  @SerialName("FoxMeta_setFirstUUID")
  public data class FirstUUID(
    public val arg0: UUID
  ) : MobMeta() {
    public override fun apply(entity: Entity): Unit {
      (entity as? net.minestom.server.entity.metadata.animal.FoxMeta ?: return).setFirstUUID(arg0)
    }
  }

  @Serializable
  @SerialName("FoxMeta_setSecondUUID")
  public data class SecondUUID(
    public val arg0: UUID
  ) : MobMeta() {
    public override fun apply(entity: Entity): Unit {
      (entity as? net.minestom.server.entity.metadata.animal.FoxMeta ?: return).setSecondUUID(arg0)
    }
  }

  @Serializable
  @SerialName("FoxMeta_setSitting")
  public data class Sitting(
    public val arg0: Boolean
  ) : MobMeta() {
    public override fun apply(entity: Entity): Unit {
      (entity as? net.minestom.server.entity.metadata.animal.FoxMeta ?: return).setSitting(arg0)
    }
  }
}
