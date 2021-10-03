package world.cepi.mob.property

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import net.minestom.server.entity.Entity
import net.minestom.server.entity.EquipmentSlot
import net.minestom.server.inventory.EquipmentHandler
import net.minestom.server.item.ItemStack
import world.cepi.kstom.command.arguments.context.OffItemContextParser
import world.cepi.kstom.command.arguments.generation.annotations.ParameterContext
import world.cepi.kstom.serializer.ItemStackSerializer
import world.cepi.mob.meta.MobMeta

@Serializable
@SerialName("helmet")
data class HelmetMeta(
    @param:ParameterContext(OffItemContextParser::class)
    @Serializable(with = ItemStackSerializer::class)
    val itemStack: ItemStack
) : MobMeta() {
    override fun apply(entity: Entity) {
        (entity as? EquipmentHandler ?: return).setEquipment(EquipmentSlot.HELMET, itemStack)
    }
}

@Serializable
@SerialName("chestplate")
data class ChestplateMeta(
    @param:ParameterContext(OffItemContextParser::class)
    @Serializable(with = ItemStackSerializer::class)
    val itemStack: ItemStack
) : MobMeta() {
    override fun apply(entity: Entity) {
        (entity as? EquipmentHandler ?: return).setEquipment(EquipmentSlot.CHESTPLATE, itemStack)
    }
}

@SerialName("leggings")
data class LeggingsMeta(
    @param:ParameterContext(OffItemContextParser::class)
    @Serializable(with = ItemStackSerializer::class)
    val itemStack: ItemStack
) : MobMeta() {
    override fun apply(entity: Entity) {
        (entity as? EquipmentHandler ?: return).setEquipment(EquipmentSlot.LEGGINGS, itemStack)
    }
}

@Serializable
@SerialName("boots")
data class BootsMeta(
    @param:ParameterContext(OffItemContextParser::class)
    @Serializable(with = ItemStackSerializer::class)
    val itemStack: ItemStack
) : MobMeta() {
    override fun apply(entity: Entity) {
        (entity as? EquipmentHandler ?: return).setEquipment(EquipmentSlot.BOOTS, itemStack)
    }
}

@Serializable
@SerialName("off_hand")
data class OffHandMeta(
    @param:ParameterContext(OffItemContextParser::class)
    @Serializable(with = ItemStackSerializer::class)
    val itemStack: ItemStack
) : MobMeta() {
    override fun apply(entity: Entity) {
        (entity as? EquipmentHandler ?: return).setEquipment(EquipmentSlot.OFF_HAND, itemStack)
    }
}

@Serializable
@SerialName("main_hand")
data class MainHandMeta(
    @param:ParameterContext(OffItemContextParser::class)
    @Serializable(with = ItemStackSerializer::class)
    val itemStack: ItemStack
) : MobMeta() {
    override fun apply(entity: Entity) {
        (entity as? EquipmentHandler ?: return).setEquipment(EquipmentSlot.MAIN_HAND, itemStack)
    }
}