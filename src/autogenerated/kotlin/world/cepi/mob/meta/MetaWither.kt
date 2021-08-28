package world.cepi.mob.meta

import kotlin.Int
import kotlin.Unit
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import net.minestom.server.entity.Entity
import net.minestom.server.entity.metadata.monster.WitherMeta

@Serializable
public object MetaWither {
  @Serializable
  @SerialName("MetaWither_setCenterHead")
  public data class CenterHead(
    public val arg0: Entity
  ) : MobMeta() {
    public override fun apply(entity: Entity): Unit {
      (entity as? WitherMeta ?: return).setCenterHead(arg0)
    }
  }

  @Serializable
  @SerialName("MetaWither_setLeftHead")
  public data class LeftHead(
    public val arg0: Entity
  ) : MobMeta() {
    public override fun apply(entity: Entity): Unit {
      (entity as? WitherMeta ?: return).setLeftHead(arg0)
    }
  }

  @Serializable
  @SerialName("MetaWither_setRightHead")
  public data class RightHead(
    public val arg0: Entity
  ) : MobMeta() {
    public override fun apply(entity: Entity): Unit {
      (entity as? WitherMeta ?: return).setRightHead(arg0)
    }
  }

  @Serializable
  @SerialName("MetaWither_setInvulnerableTime")
  public data class InvulnerableTime(
    public val arg0: Int
  ) : MobMeta() {
    public override fun apply(entity: Entity): Unit {
      (entity as? WitherMeta ?: return).setInvulnerableTime(arg0)
    }
  }
}
