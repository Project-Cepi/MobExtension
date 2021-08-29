package world.cepi.mob.meta.generated

import kotlin.Boolean
import kotlin.Float
import kotlin.Int
import kotlin.Unit
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import net.minestom.server.coordinate.Point
import net.minestom.server.entity.Entity
import net.minestom.server.entity.Player
import net.minestom.server.entity.metadata.LivingEntityMeta
import world.cepi.mob.meta.MobMeta

@Serializable
public object MetaLivingEntity {
  @Serializable
  @SerialName("MetaLivingEntity_setHandActive")
  public data class HandActive(
    public val arg0: Boolean
  ) : MobMeta() {
    public override fun apply(entity: Entity): Unit {
      (entity.entityMeta as? LivingEntityMeta ?: return).setHandActive(arg0)
    }
  }

  @Serializable
  @SerialName("MetaLivingEntity_setActiveHand")
  public data class ActiveHand(
    public val arg0: Player.Hand
  ) : MobMeta() {
    public override fun apply(entity: Entity): Unit {
      (entity.entityMeta as? LivingEntityMeta ?: return).setActiveHand(arg0)
    }
  }

  @Serializable
  @SerialName("MetaLivingEntity_setInRiptideSpinAttack")
  public data class InRiptideSpinAttack(
    public val arg0: Boolean
  ) : MobMeta() {
    public override fun apply(entity: Entity): Unit {
      (entity.entityMeta as? LivingEntityMeta ?: return).setInRiptideSpinAttack(arg0)
    }
  }

  @Serializable
  @SerialName("MetaLivingEntity_setHealth")
  public data class Health(
    public val arg0: Float
  ) : MobMeta() {
    public override fun apply(entity: Entity): Unit {
      (entity.entityMeta as? LivingEntityMeta ?: return).setHealth(arg0)
    }
  }

  @Serializable
  @SerialName("MetaLivingEntity_setPotionEffectColor")
  public data class PotionEffectColor(
    public val arg0: Int
  ) : MobMeta() {
    public override fun apply(entity: Entity): Unit {
      (entity.entityMeta as? LivingEntityMeta ?: return).setPotionEffectColor(arg0)
    }
  }

  @Serializable
  @SerialName("MetaLivingEntity_setPotionEffectAmbient")
  public data class PotionEffectAmbient(
    public val arg0: Boolean
  ) : MobMeta() {
    public override fun apply(entity: Entity): Unit {
      (entity.entityMeta as? LivingEntityMeta ?: return).setPotionEffectAmbient(arg0)
    }
  }

  @Serializable
  @SerialName("MetaLivingEntity_setArrowCount")
  public data class ArrowCount(
    public val arg0: Int
  ) : MobMeta() {
    public override fun apply(entity: Entity): Unit {
      (entity.entityMeta as? LivingEntityMeta ?: return).setArrowCount(arg0)
    }
  }

  @Serializable
  @SerialName("MetaLivingEntity_setHealthAddedByAbsorption")
  public data class HealthAddedByAbsorption(
    public val arg0: Int
  ) : MobMeta() {
    public override fun apply(entity: Entity): Unit {
      (entity.entityMeta as? LivingEntityMeta ?: return).setHealthAddedByAbsorption(arg0)
    }
  }

  @Serializable
  @SerialName("MetaLivingEntity_setBedInWhichSleepingPosition")
  public data class BedInWhichSleepingPosition(
    public val arg0: Point
  ) : MobMeta() {
    public override fun apply(entity: Entity): Unit {
      (entity.entityMeta as? LivingEntityMeta ?: return).setBedInWhichSleepingPosition(arg0)
    }
  }
}
