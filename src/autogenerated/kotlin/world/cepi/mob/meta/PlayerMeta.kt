package world.cepi.mob.meta

import kotlin.Boolean
import kotlin.Float
import kotlin.Int
import kotlin.Unit
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import net.minestom.server.entity.Entity
import net.minestom.server.entity.metadata.MobMeta
import org.jglrxavpok.hephaistos.nbt.NBT

public object PlayerMeta {
  @Serializable
  @SerialName("PlayerMeta_setAdditionalHearts")
  public data class AdditionalHearts(
    arg0: Float
  ) : MobMeta() {
    public override fun apply(entity: Entity): Unit {
      (entity as? net.minestom.server.entity.metadata.PlayerMeta ?:
          return).setAdditionalHearts(arg0)
    }
  }

  @Serializable
  @SerialName("PlayerMeta_setScore")
  public data class Score(
    arg0: Int
  ) : MobMeta() {
    public override fun apply(entity: Entity): Unit {
      (entity as? net.minestom.server.entity.metadata.PlayerMeta ?: return).setScore(arg0)
    }
  }

  @Serializable
  @SerialName("PlayerMeta_setCapeEnabled")
  public data class CapeEnabled(
    arg0: Boolean
  ) : MobMeta() {
    public override fun apply(entity: Entity): Unit {
      (entity as? net.minestom.server.entity.metadata.PlayerMeta ?: return).setCapeEnabled(arg0)
    }
  }

  @Serializable
  @SerialName("PlayerMeta_setJacketEnabled")
  public data class JacketEnabled(
    arg0: Boolean
  ) : MobMeta() {
    public override fun apply(entity: Entity): Unit {
      (entity as? net.minestom.server.entity.metadata.PlayerMeta ?: return).setJacketEnabled(arg0)
    }
  }

  @Serializable
  @SerialName("PlayerMeta_setLeftSleeveEnabled")
  public data class LeftSleeveEnabled(
    arg0: Boolean
  ) : MobMeta() {
    public override fun apply(entity: Entity): Unit {
      (entity as? net.minestom.server.entity.metadata.PlayerMeta ?:
          return).setLeftSleeveEnabled(arg0)
    }
  }

  @Serializable
  @SerialName("PlayerMeta_setRightSleeveEnabled")
  public data class RightSleeveEnabled(
    arg0: Boolean
  ) : MobMeta() {
    public override fun apply(entity: Entity): Unit {
      (entity as? net.minestom.server.entity.metadata.PlayerMeta ?:
          return).setRightSleeveEnabled(arg0)
    }
  }

  @Serializable
  @SerialName("PlayerMeta_setLeftLegEnabled")
  public data class LeftLegEnabled(
    arg0: Boolean
  ) : MobMeta() {
    public override fun apply(entity: Entity): Unit {
      (entity as? net.minestom.server.entity.metadata.PlayerMeta ?: return).setLeftLegEnabled(arg0)
    }
  }

  @Serializable
  @SerialName("PlayerMeta_setRightLegEnabled")
  public data class RightLegEnabled(
    arg0: Boolean
  ) : MobMeta() {
    public override fun apply(entity: Entity): Unit {
      (entity as? net.minestom.server.entity.metadata.PlayerMeta ?: return).setRightLegEnabled(arg0)
    }
  }

  @Serializable
  @SerialName("PlayerMeta_setHatEnabled")
  public data class HatEnabled(
    arg0: Boolean
  ) : MobMeta() {
    public override fun apply(entity: Entity): Unit {
      (entity as? net.minestom.server.entity.metadata.PlayerMeta ?: return).setHatEnabled(arg0)
    }
  }

  @Serializable
  @SerialName("PlayerMeta_setRightMainHand")
  public data class RightMainHand(
    arg0: Boolean
  ) : MobMeta() {
    public override fun apply(entity: Entity): Unit {
      (entity as? net.minestom.server.entity.metadata.PlayerMeta ?: return).setRightMainHand(arg0)
    }
  }

  @Serializable
  @SerialName("PlayerMeta_setLeftShoulderEntityData")
  public data class LeftShoulderEntityData(
    arg0: NBT
  ) : MobMeta() {
    public override fun apply(entity: Entity): Unit {
      (entity as? net.minestom.server.entity.metadata.PlayerMeta ?:
          return).setLeftShoulderEntityData(arg0)
    }
  }

  @Serializable
  @SerialName("PlayerMeta_setRightShoulderEntityData")
  public data class RightShoulderEntityData(
    arg0: NBT
  ) : MobMeta() {
    public override fun apply(entity: Entity): Unit {
      (entity as? net.minestom.server.entity.metadata.PlayerMeta ?:
          return).setRightShoulderEntityData(arg0)
    }
  }
}
