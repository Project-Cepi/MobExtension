package world.cepi.mob.meta

import kotlin.Int
import kotlin.Unit
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import net.minestom.server.entity.Entity
import net.minestom.server.entity.metadata.MobMeta

public object WitherMeta {
  @Serializable
  @SerialName("WitherMeta_setCenterHead")
  public data class CenterHead(
    arg0: Entity
  ) : MobMeta() {
    public override fun apply(entity: Entity): Unit {
      (entity as? net.minestom.server.entity.metadata.monster.WitherMeta ?:
          return).setCenterHead(arg0)
    }
  }

  @Serializable
  @SerialName("WitherMeta_setLeftHead")
  public data class LeftHead(
    arg0: Entity
  ) : MobMeta() {
    public override fun apply(entity: Entity): Unit {
      (entity as? net.minestom.server.entity.metadata.monster.WitherMeta ?:
          return).setLeftHead(arg0)
    }
  }

  @Serializable
  @SerialName("WitherMeta_setRightHead")
  public data class RightHead(
    arg0: Entity
  ) : MobMeta() {
    public override fun apply(entity: Entity): Unit {
      (entity as? net.minestom.server.entity.metadata.monster.WitherMeta ?:
          return).setRightHead(arg0)
    }
  }

  @Serializable
  @SerialName("WitherMeta_setInvulnerableTime")
  public data class InvulnerableTime(
    arg0: Int
  ) : MobMeta() {
    public override fun apply(entity: Entity): Unit {
      (entity as? net.minestom.server.entity.metadata.monster.WitherMeta ?:
          return).setInvulnerableTime(arg0)
    }
  }
}
