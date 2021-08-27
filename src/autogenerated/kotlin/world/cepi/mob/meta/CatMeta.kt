package world.cepi.mob.meta

import kotlin.Boolean
import kotlin.Int
import kotlin.Unit
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import net.minestom.server.entity.Entity
import net.minestom.server.entity.metadata.MobMeta

@Serializable
public object CatMeta {
  @Serializable
  @SerialName("CatMeta_setColor")
  public data class Color(
    public val arg0: net.minestom.server.entity.metadata.animal.tameable.CatMeta.Color
  ) : MobMeta() {
    public override fun apply(entity: Entity): Unit {
      (entity as? net.minestom.server.entity.metadata.animal.tameable.CatMeta ?:
          return).setColor(arg0)
    }
  }

  @Serializable
  @SerialName("CatMeta_setCollarColor")
  public data class CollarColor(
    public val arg0: Int
  ) : MobMeta() {
    public override fun apply(entity: Entity): Unit {
      (entity as? net.minestom.server.entity.metadata.animal.tameable.CatMeta ?:
          return).setCollarColor(arg0)
    }
  }

  @Serializable
  @SerialName("CatMeta_setLying")
  public data class Lying(
    public val arg0: Boolean
  ) : MobMeta() {
    public override fun apply(entity: Entity): Unit {
      (entity as? net.minestom.server.entity.metadata.animal.tameable.CatMeta ?:
          return).setLying(arg0)
    }
  }

  @Serializable
  @SerialName("CatMeta_setRelaxed")
  public data class Relaxed(
    public val arg0: Boolean
  ) : MobMeta() {
    public override fun apply(entity: Entity): Unit {
      (entity as? net.minestom.server.entity.metadata.animal.tameable.CatMeta ?:
          return).setRelaxed(arg0)
    }
  }
}
