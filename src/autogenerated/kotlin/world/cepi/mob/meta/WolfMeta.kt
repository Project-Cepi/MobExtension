package world.cepi.mob.meta

import kotlin.Boolean
import kotlin.Int
import kotlin.Unit
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import net.minestom.server.entity.Entity
import net.minestom.server.entity.metadata.MobMeta

public object WolfMeta {
  @Serializable
  @SerialName("WolfMeta_setCollarColor")
  public data class CollarColor(
    arg0: Int
  ) : MobMeta() {
    public override fun apply(entity: Entity): Unit {
      (entity as? net.minestom.server.entity.metadata.animal.tameable.WolfMeta ?:
          return).setCollarColor(arg0)
    }
  }

  @Serializable
  @SerialName("WolfMeta_setBegging")
  public data class Begging(
    arg0: Boolean
  ) : MobMeta() {
    public override fun apply(entity: Entity): Unit {
      (entity as? net.minestom.server.entity.metadata.animal.tameable.WolfMeta ?:
          return).setBegging(arg0)
    }
  }

  @Serializable
  @SerialName("WolfMeta_setAngerTime")
  public data class AngerTime(
    arg0: Int
  ) : MobMeta() {
    public override fun apply(entity: Entity): Unit {
      (entity as? net.minestom.server.entity.metadata.animal.tameable.WolfMeta ?:
          return).setAngerTime(arg0)
    }
  }
}
