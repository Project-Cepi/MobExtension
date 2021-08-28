package world.cepi.mob.meta

import kotlin.Boolean
import kotlin.Int
import kotlin.Unit
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import net.minestom.server.entity.Entity
import net.minestom.server.entity.metadata.animal.PandaMeta

@Serializable
public object MetaPanda {
  @Serializable
  @SerialName("MetaPanda_setSneezeTimer")
  public data class SneezeTimer(
    public val arg0: Int
  ) : MobMeta() {
    public override fun apply(entity: Entity): Unit {
      (entity as? PandaMeta ?: return).setSneezeTimer(arg0)
    }
  }

  @Serializable
  @SerialName("MetaPanda_setSitting")
  public data class Sitting(
    public val arg0: Boolean
  ) : MobMeta() {
    public override fun apply(entity: Entity): Unit {
      (entity as? PandaMeta ?: return).setSitting(arg0)
    }
  }

  @Serializable
  @SerialName("MetaPanda_setBreedTimer")
  public data class BreedTimer(
    public val arg0: Int
  ) : MobMeta() {
    public override fun apply(entity: Entity): Unit {
      (entity as? PandaMeta ?: return).setBreedTimer(arg0)
    }
  }

  @Serializable
  @SerialName("MetaPanda_setEatTimer")
  public data class EatTimer(
    public val arg0: Int
  ) : MobMeta() {
    public override fun apply(entity: Entity): Unit {
      (entity as? PandaMeta ?: return).setEatTimer(arg0)
    }
  }

  @Serializable
  @SerialName("MetaPanda_setMainGene")
  public data class MainGene(
    public val arg0: PandaMeta.Gene
  ) : MobMeta() {
    public override fun apply(entity: Entity): Unit {
      (entity as? PandaMeta ?: return).setMainGene(arg0)
    }
  }

  @Serializable
  @SerialName("MetaPanda_setHiddenGene")
  public data class HiddenGene(
    public val arg0: PandaMeta.Gene
  ) : MobMeta() {
    public override fun apply(entity: Entity): Unit {
      (entity as? PandaMeta ?: return).setHiddenGene(arg0)
    }
  }

  @Serializable
  @SerialName("MetaPanda_setSneezing")
  public data class Sneezing(
    public val arg0: Boolean
  ) : MobMeta() {
    public override fun apply(entity: Entity): Unit {
      (entity as? PandaMeta ?: return).setSneezing(arg0)
    }
  }

  @Serializable
  @SerialName("MetaPanda_setRolling")
  public data class Rolling(
    public val arg0: Boolean
  ) : MobMeta() {
    public override fun apply(entity: Entity): Unit {
      (entity as? PandaMeta ?: return).setRolling(arg0)
    }
  }

  @Serializable
  @SerialName("MetaPanda_setOnBack")
  public data class OnBack(
    public val arg0: Boolean
  ) : MobMeta() {
    public override fun apply(entity: Entity): Unit {
      (entity as? PandaMeta ?: return).setOnBack(arg0)
    }
  }
}
