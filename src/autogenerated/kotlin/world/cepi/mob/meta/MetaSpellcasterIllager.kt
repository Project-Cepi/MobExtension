package world.cepi.mob.meta

import kotlin.Unit
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import net.minestom.server.entity.Entity
import net.minestom.server.entity.metadata.monster.raider.SpellcasterIllagerMeta

@Serializable
public object MetaSpellcasterIllager {
  @Serializable
  @SerialName("MetaSpellcasterIllager_setSpell")
  public data class Spell(
    public val arg0: SpellcasterIllagerMeta.Spell
  ) : MobMeta() {
    public override fun apply(entity: Entity): Unit {
      (entity.entityMeta as? SpellcasterIllagerMeta ?: return).setSpell(arg0)
    }
  }
}
