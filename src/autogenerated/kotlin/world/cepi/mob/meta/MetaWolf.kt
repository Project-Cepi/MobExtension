package world.cepi.mob.meta

import kotlin.Boolean
import kotlin.Int
import kotlin.Unit
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import net.minestom.server.entity.Entity
import net.minestom.server.entity.metadata.animal.tameable.WolfMeta

@Serializable
public object MetaWolf {
  @Serializable
  @SerialName("MetaWolf_setCollarColor")
  public data class CollarColor(
    public val arg0: Int
  ) : MobMeta() {
    public override fun apply(entity: Entity): Unit {
      (entity as? WolfMeta ?: return).setCollarColor(arg0)
    }
  }

  @Serializable
  @SerialName("MetaWolf_setBegging")
  public data class Begging(
    public val arg0: Boolean
  ) : MobMeta() {
    public override fun apply(entity: Entity): Unit {
      (entity as? WolfMeta ?: return).setBegging(arg0)
    }
  }

  @Serializable
  @SerialName("MetaWolf_setAngerTime")
  public data class AngerTime(
    public val arg0: Int
  ) : MobMeta() {
    public override fun apply(entity: Entity): Unit {
      (entity as? WolfMeta ?: return).setAngerTime(arg0)
    }
  }
}
