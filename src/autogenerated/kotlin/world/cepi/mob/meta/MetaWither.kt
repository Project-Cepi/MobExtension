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
  @SerialName("MetaWither_setInvulnerableTime")
  public data class InvulnerableTime(
    public val arg0: Int
  ) : MobMeta() {
    public override fun apply(entity: Entity): Unit {
      (entity.entityMeta as? WitherMeta ?: return).setInvulnerableTime(arg0)
    }
  }
}
