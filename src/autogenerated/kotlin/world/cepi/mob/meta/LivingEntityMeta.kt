package world.cepi.mob.meta

import kotlin.Boolean
import kotlin.Float
import kotlin.Int
import kotlin.Unit
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import net.minestom.server.coordinate.Point
import net.minestom.server.entity.Entity
import net.minestom.server.entity.Player
import net.minestom.server.entity.metadata.MobMeta

public object LivingEntityMeta {
  @Serializable
  @SerialName("LivingEntityMeta_setHandActive")
  public data class HandActive(
    arg0: Boolean
  ) : MobMeta() {
    public override fun apply(entity: Entity): Unit {
      (entity as? net.minestom.server.entity.metadata.LivingEntityMeta ?:
          return).setHandActive(arg0)
    }
  }

  @Serializable
  @SerialName("LivingEntityMeta_setActiveHand")
  public data class ActiveHand(
    arg0: Player.Hand
  ) : MobMeta() {
    public override fun apply(entity: Entity): Unit {
      (entity as? net.minestom.server.entity.metadata.LivingEntityMeta ?:
          return).setActiveHand(arg0)
    }
  }

  @Serializable
  @SerialName("LivingEntityMeta_setInRiptideSpinAttack")
  public data class InRiptideSpinAttack(
    arg0: Boolean
  ) : MobMeta() {
    public override fun apply(entity: Entity): Unit {
      (entity as? net.minestom.server.entity.metadata.LivingEntityMeta ?:
          return).setInRiptideSpinAttack(arg0)
    }
  }

  @Serializable
  @SerialName("LivingEntityMeta_setHealth")
  public data class Health(
    arg0: Float
  ) : MobMeta() {
    public override fun apply(entity: Entity): Unit {
      (entity as? net.minestom.server.entity.metadata.LivingEntityMeta ?: return).setHealth(arg0)
    }
  }

  @Serializable
  @SerialName("LivingEntityMeta_setPotionEffectColor")
  public data class PotionEffectColor(
    arg0: Int
  ) : MobMeta() {
    public override fun apply(entity: Entity): Unit {
      (entity as? net.minestom.server.entity.metadata.LivingEntityMeta ?:
          return).setPotionEffectColor(arg0)
    }
  }

  @Serializable
  @SerialName("LivingEntityMeta_setPotionEffectAmbient")
  public data class PotionEffectAmbient(
    arg0: Boolean
  ) : MobMeta() {
    public override fun apply(entity: Entity): Unit {
      (entity as? net.minestom.server.entity.metadata.LivingEntityMeta ?:
          return).setPotionEffectAmbient(arg0)
    }
  }

  @Serializable
  @SerialName("LivingEntityMeta_setArrowCount")
  public data class ArrowCount(
    arg0: Int
  ) : MobMeta() {
    public override fun apply(entity: Entity): Unit {
      (entity as? net.minestom.server.entity.metadata.LivingEntityMeta ?:
          return).setArrowCount(arg0)
    }
  }

  @Serializable
  @SerialName("LivingEntityMeta_setHealthAddedByAbsorption")
  public data class HealthAddedByAbsorption(
    arg0: Int
  ) : MobMeta() {
    public override fun apply(entity: Entity): Unit {
      (entity as? net.minestom.server.entity.metadata.LivingEntityMeta ?:
          return).setHealthAddedByAbsorption(arg0)
    }
  }

  @Serializable
  @SerialName("LivingEntityMeta_setBedInWhichSleepingPosition")
  public data class BedInWhichSleepingPosition(
    arg0: Point
  ) : MobMeta() {
    public override fun apply(entity: Entity): Unit {
      (entity as? net.minestom.server.entity.metadata.LivingEntityMeta ?:
          return).setBedInWhichSleepingPosition(arg0)
    }
  }
}
