package world.cepi.mob.meta

import kotlin.Unit
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import net.minestom.server.entity.Entity
import net.minestom.server.entity.metadata.animal.tameable.ParrotMeta

@Serializable
public object MetaParrot {
  @Serializable
  @SerialName("MetaParrot_setColor")
  public data class Color(
    public val arg0: ParrotMeta.Color
  ) : MobMeta() {
    public override fun apply(entity: Entity): Unit {
      (entity.entityMeta as? ParrotMeta ?: return).setColor(arg0)
    }
  }
}
