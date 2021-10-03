package world.cepi.mob.property

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import net.minestom.server.entity.EntityCreature
import net.minestom.server.entity.EquipmentSlot
import net.minestom.server.inventory.EquipmentHandler
import net.minestom.server.item.ItemStack
import world.cepi.kstom.command.arguments.context.OffItemContextParser
import world.cepi.kstom.command.arguments.generation.annotations.ParameterContext
import world.cepi.kstom.serializer.ItemStackSerializer

@Serializable
@SerialName("helmet")
data class HelmetProperty(
    @param:ParameterContext(OffItemContextParser::class)
    @Serializable(with = ItemStackSerializer::class)
    val itemStack: ItemStack
) : MobProperty() {
    override fun apply(creature: EntityCreature) {
        (creature as? EquipmentHandler ?: return).setEquipment(EquipmentSlot.HELMET, itemStack)
    }
}

@Serializable
@SerialName("chestplate")
data class ChestplateProperty(
    @param:ParameterContext(OffItemContextParser::class)
    @Serializable(with = ItemStackSerializer::class)
    val itemStack: ItemStack
) : MobProperty() {
    override fun apply(creature: EntityCreature) {
        (creature as? EquipmentHandler ?: return).setEquipment(EquipmentSlot.CHESTPLATE, itemStack)
    }
}

@SerialName("leggings")
data class LeggingsProperty(
    @param:ParameterContext(OffItemContextParser::class)
    @Serializable(with = ItemStackSerializer::class)
    val itemStack: ItemStack
) : MobProperty() {
    override fun apply(creature: EntityCreature) {
        (creature as? EquipmentHandler ?: return).setEquipment(EquipmentSlot.LEGGINGS, itemStack)
    }
}

@Serializable
@SerialName("boots")
data class BootsProperty(
    @param:ParameterContext(OffItemContextParser::class)
    @Serializable(with = ItemStackSerializer::class)
    val itemStack: ItemStack
) : MobProperty() {
    override fun apply(creature: EntityCreature) {
        (creature as? EquipmentHandler ?: return).setEquipment(EquipmentSlot.BOOTS, itemStack)
    }
}

@Serializable
@SerialName("off_hand")
data class OffHandProperty(
    @param:ParameterContext(OffItemContextParser::class)
    @Serializable(with = ItemStackSerializer::class)
    val itemStack: ItemStack
) : MobProperty() {
    override fun apply(creature: EntityCreature) {
        (creature as? EquipmentHandler ?: return).setEquipment(EquipmentSlot.OFF_HAND, itemStack)
    }
}

@Serializable
@SerialName("main_hand")
data class MainHandProperty(
    @param:ParameterContext(OffItemContextParser::class)
    @Serializable(with = ItemStackSerializer::class)
    val itemStack: ItemStack
) : MobProperty() {
    override fun apply(creature: EntityCreature) {
        (creature as? EquipmentHandler ?: return).setEquipment(EquipmentSlot.MAIN_HAND, itemStack)
    }
}