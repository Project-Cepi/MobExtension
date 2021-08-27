package world.cepi.mob.meta

import kotlin.Boolean
import kotlin.Int
import kotlin.Unit
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import net.minestom.server.entity.Entity
import net.minestom.server.entity.metadata.MobMeta

public object ThrownTridentMeta {
  @Serializable
  @SerialName("ThrownTridentMeta_setLoyaltyLevel")
  public data class LoyaltyLevel(
    arg0: Int
  ) : MobMeta() {
    public override fun apply(entity: Entity): Unit {
      (entity as? net.minestom.server.entity.metadata.arrow.ThrownTridentMeta ?:
          return).setLoyaltyLevel(arg0)
    }
  }

  @Serializable
  @SerialName("ThrownTridentMeta_setHasEnchantmentGlint")
  public data class HasEnchantmentGlint(
    arg0: Boolean
  ) : MobMeta() {
    public override fun apply(entity: Entity): Unit {
      (entity as? net.minestom.server.entity.metadata.arrow.ThrownTridentMeta ?:
          return).setHasEnchantmentGlint(arg0)
    }
  }
}
