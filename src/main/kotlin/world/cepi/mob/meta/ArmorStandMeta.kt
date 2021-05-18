package world.cepi.mob.meta

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import net.minestom.server.entity.Entity
import net.minestom.server.entity.metadata.other.ArmorStandMeta

@Serializable
@SerialName("armor_stand-arm")
data class ArmorStandArmMeta(@SerialName("value") val hasArms: Boolean) : MobMeta() {
    override fun apply(entity: Entity) {
        (entity.entityMeta as? ArmorStandMeta ?: return).isHasArms = hasArms
    }
}

@Serializable
@SerialName("armor_stand-small")
data class ArmorStandSmallMeta(@SerialName("value") val isSmall: Boolean) : MobMeta() {
    override fun apply(entity: Entity) {
        (entity.entityMeta as? ArmorStandMeta ?: return).isSmall = isSmall
    }
}

@Serializable
@SerialName("armor_stand-base_plate")
data class ArmorStandBasePlateMeta(@SerialName("value") val hasBasePlate: Boolean) : MobMeta() {
    override fun apply(entity: Entity) {
        (entity.entityMeta as? ArmorStandMeta ?: return).isHasNoBasePlate = hasBasePlate
    }
}