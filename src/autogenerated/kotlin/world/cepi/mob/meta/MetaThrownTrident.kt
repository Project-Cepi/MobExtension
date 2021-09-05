package world.cepi.mob.meta

import kotlin.Boolean
import kotlin.Int
import kotlin.Unit
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import net.minestom.server.entity.Entity
import net.minestom.server.entity.metadata.arrow.ThrownTridentMeta

@Serializable
public object MetaThrownTrident {
  @Serializable
  @SerialName("MetaThrownTrident_setLoyaltyLevel")
  public data class LoyaltyLevel(
    public val arg0: Int
  ) : MobMeta() {
    public override fun apply(entity: Entity): Unit {
      (entity.entityMeta as? ThrownTridentMeta ?: return).setLoyaltyLevel(arg0)
    }
  }

  @Serializable
  @SerialName("MetaThrownTrident_setHasEnchantmentGlint")
  public data class HasEnchantmentGlint(
    public val arg0: Boolean
  ) : MobMeta() {
    public override fun apply(entity: Entity): Unit {
      (entity.entityMeta as? ThrownTridentMeta ?: return).setHasEnchantmentGlint(arg0)
    }
  }
}
