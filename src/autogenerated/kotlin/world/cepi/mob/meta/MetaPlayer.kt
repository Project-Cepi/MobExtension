package world.cepi.mob.meta

import kotlin.Boolean
import kotlin.Float
import kotlin.Int
import kotlin.Unit
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import net.minestom.server.entity.Entity
import net.minestom.server.entity.metadata.PlayerMeta
import org.jglrxavpok.hephaistos.nbt.NBT

@Serializable
public object MetaPlayer {
  @Serializable
  @SerialName("MetaPlayer_setAdditionalHearts")
  public data class AdditionalHearts(
    public val arg0: Float
  ) : MobMeta() {
    public override fun apply(entity: Entity): Unit {
      (entity.entityMeta as? PlayerMeta ?: return).setAdditionalHearts(arg0)
    }
  }

  @Serializable
  @SerialName("MetaPlayer_setScore")
  public data class Score(
    public val arg0: Int
  ) : MobMeta() {
    public override fun apply(entity: Entity): Unit {
      (entity.entityMeta as? PlayerMeta ?: return).setScore(arg0)
    }
  }

  @Serializable
  @SerialName("MetaPlayer_setCapeEnabled")
  public data class CapeEnabled(
    public val arg0: Boolean
  ) : MobMeta() {
    public override fun apply(entity: Entity): Unit {
      (entity.entityMeta as? PlayerMeta ?: return).setCapeEnabled(arg0)
    }
  }

  @Serializable
  @SerialName("MetaPlayer_setJacketEnabled")
  public data class JacketEnabled(
    public val arg0: Boolean
  ) : MobMeta() {
    public override fun apply(entity: Entity): Unit {
      (entity.entityMeta as? PlayerMeta ?: return).setJacketEnabled(arg0)
    }
  }

  @Serializable
  @SerialName("MetaPlayer_setLeftSleeveEnabled")
  public data class LeftSleeveEnabled(
    public val arg0: Boolean
  ) : MobMeta() {
    public override fun apply(entity: Entity): Unit {
      (entity.entityMeta as? PlayerMeta ?: return).setLeftSleeveEnabled(arg0)
    }
  }

  @Serializable
  @SerialName("MetaPlayer_setRightSleeveEnabled")
  public data class RightSleeveEnabled(
    public val arg0: Boolean
  ) : MobMeta() {
    public override fun apply(entity: Entity): Unit {
      (entity.entityMeta as? PlayerMeta ?: return).setRightSleeveEnabled(arg0)
    }
  }

  @Serializable
  @SerialName("MetaPlayer_setLeftLegEnabled")
  public data class LeftLegEnabled(
    public val arg0: Boolean
  ) : MobMeta() {
    public override fun apply(entity: Entity): Unit {
      (entity.entityMeta as? PlayerMeta ?: return).setLeftLegEnabled(arg0)
    }
  }

  @Serializable
  @SerialName("MetaPlayer_setRightLegEnabled")
  public data class RightLegEnabled(
    public val arg0: Boolean
  ) : MobMeta() {
    public override fun apply(entity: Entity): Unit {
      (entity.entityMeta as? PlayerMeta ?: return).setRightLegEnabled(arg0)
    }
  }

  @Serializable
  @SerialName("MetaPlayer_setHatEnabled")
  public data class HatEnabled(
    public val arg0: Boolean
  ) : MobMeta() {
    public override fun apply(entity: Entity): Unit {
      (entity.entityMeta as? PlayerMeta ?: return).setHatEnabled(arg0)
    }
  }

  @Serializable
  @SerialName("MetaPlayer_setRightMainHand")
  public data class RightMainHand(
    public val arg0: Boolean
  ) : MobMeta() {
    public override fun apply(entity: Entity): Unit {
      (entity.entityMeta as? PlayerMeta ?: return).setRightMainHand(arg0)
    }
  }

  @Serializable
  @SerialName("MetaPlayer_setLeftShoulderEntityData")
  public data class LeftShoulderEntityData(
    public val arg0: NBT
  ) : MobMeta() {
    public override fun apply(entity: Entity): Unit {
      (entity.entityMeta as? PlayerMeta ?: return).setLeftShoulderEntityData(arg0)
    }
  }

  @Serializable
  @SerialName("MetaPlayer_setRightShoulderEntityData")
  public data class RightShoulderEntityData(
    public val arg0: NBT
  ) : MobMeta() {
    public override fun apply(entity: Entity): Unit {
      (entity.entityMeta as? PlayerMeta ?: return).setRightShoulderEntityData(arg0)
    }
  }
}
