package world.cepi.mob.meta.generated

import java.util.UUID
import kotlin.Boolean
import kotlin.Unit
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import net.minestom.server.entity.Entity
import net.minestom.server.entity.metadata.animal.FoxMeta
import world.cepi.kstom.serializer.UUIDSerializer
import world.cepi.mob.meta.MobMeta

@Serializable
public object MetaFox {
  @Serializable
  @SerialName("MetaFox_setType")
  public data class Type(
    public val arg0: FoxMeta.Type
  ) : MobMeta() {
    public override fun apply(entity: Entity): Unit {
      (entity.entityMeta as? FoxMeta ?: return).setType(arg0)
    }
  }

  @Serializable
  @SerialName("MetaFox_setFoxSneaking")
  public data class FoxSneaking(
    public val arg0: Boolean
  ) : MobMeta() {
    public override fun apply(entity: Entity): Unit {
      (entity.entityMeta as? FoxMeta ?: return).setFoxSneaking(arg0)
    }
  }

  @Serializable
  @SerialName("MetaFox_setInterested")
  public data class Interested(
    public val arg0: Boolean
  ) : MobMeta() {
    public override fun apply(entity: Entity): Unit {
      (entity.entityMeta as? FoxMeta ?: return).setInterested(arg0)
    }
  }

  @Serializable
  @SerialName("MetaFox_setPouncing")
  public data class Pouncing(
    public val arg0: Boolean
  ) : MobMeta() {
    public override fun apply(entity: Entity): Unit {
      (entity.entityMeta as? FoxMeta ?: return).setPouncing(arg0)
    }
  }

  @Serializable
  @SerialName("MetaFox_setSleeping")
  public data class Sleeping(
    public val arg0: Boolean
  ) : MobMeta() {
    public override fun apply(entity: Entity): Unit {
      (entity.entityMeta as? FoxMeta ?: return).setSleeping(arg0)
    }
  }

  @Serializable
  @SerialName("MetaFox_setFaceplanted")
  public data class Faceplanted(
    public val arg0: Boolean
  ) : MobMeta() {
    public override fun apply(entity: Entity): Unit {
      (entity.entityMeta as? FoxMeta ?: return).setFaceplanted(arg0)
    }
  }

  @Serializable
  @SerialName("MetaFox_setDefending")
  public data class Defending(
    public val arg0: Boolean
  ) : MobMeta() {
    public override fun apply(entity: Entity): Unit {
      (entity.entityMeta as? FoxMeta ?: return).setDefending(arg0)
    }
  }

  @Serializable
  @SerialName("MetaFox_setFirstUUID")
  public data class FirstUUID(
    @Serializable(UUIDSerializer::class)
    public val arg0: UUID
  ) : MobMeta() {
    public override fun apply(entity: Entity): Unit {
      (entity.entityMeta as? FoxMeta ?: return).setFirstUUID(arg0)
    }
  }

  @Serializable
  @SerialName("MetaFox_setSecondUUID")
  public data class SecondUUID(
    @Serializable(UUIDSerializer::class)
    public val arg0: UUID
  ) : MobMeta() {
    public override fun apply(entity: Entity): Unit {
      (entity.entityMeta as? FoxMeta ?: return).setSecondUUID(arg0)
    }
  }

  @Serializable
  @SerialName("MetaFox_setSitting")
  public data class Sitting(
    public val arg0: Boolean
  ) : MobMeta() {
    public override fun apply(entity: Entity): Unit {
      (entity.entityMeta as? FoxMeta ?: return).setSitting(arg0)
    }
  }
}
