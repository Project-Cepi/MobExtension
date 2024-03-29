package world.cepi.mob.property

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import net.kyori.adventure.text.minimessage.MiniMessage
import net.minestom.server.entity.EntityCreature
import world.cepi.kstom.adventure.asMini
import world.cepi.kstom.command.arguments.generation.annotations.DefaultBoolean
import world.cepi.kstom.command.arguments.generation.annotations.DefaultNumber

@Serializable
@SerialName("name")
data class NameProperty(
    @SerialName("value")
    val name: String,
    @DefaultNumber(1.0)
    val level: Int = 1,
    @DefaultBoolean(true)
    val alwaysVisible: Boolean = true
) : MobProperty() {
    override fun apply(creature: EntityCreature) {
        creature.customName = (if (level > 0) Component.text("[", NamedTextColor.DARK_GRAY)
            .append(Component.text(level, NamedTextColor.GRAY))
            .append(Component.text("] ", NamedTextColor.DARK_GRAY)) else Component.space())
            .append(MiniMessage.miniMessage().deserialize(name).color(NamedTextColor.GRAY))
        creature.isCustomNameVisible = alwaysVisible
    }

}