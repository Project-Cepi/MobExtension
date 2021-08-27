package world.cepi.mob.meta

import kotlin.Unit
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import net.minestom.server.entity.Entity
import net.minestom.server.entity.metadata.MobMeta

@Serializable
public object SpellcasterIllagerMeta {
  @Serializable
  @SerialName("SpellcasterIllagerMeta_setSpell")
  public data class Spell(
    public val arg0: net.minestom.server.entity.metadata.monster.raider.SpellcasterIllagerMeta.Spell
  ) : MobMeta() {
    public override fun apply(entity: Entity): Unit {
      (entity as? net.minestom.server.entity.metadata.monster.raider.SpellcasterIllagerMeta ?:
          return).setSpell(arg0)
    }
  }
}
