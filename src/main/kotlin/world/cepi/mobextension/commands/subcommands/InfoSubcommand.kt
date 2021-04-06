package world.cepi.mobextension.commands.subcommands

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import net.minestom.server.command.builder.Command
import net.minestom.server.entity.Player
import net.minestom.server.utils.time.UpdateOption
import org.jetbrains.annotations.Contract
import world.cepi.kstom.command.addSyntax
import world.cepi.mobextension.EntityData
import world.cepi.mobextension.mob
import kotlin.reflect.KProperty1
import kotlin.reflect.full.memberProperties

internal object InfoSubcommand : Command("info") {

    @Contract(pure = true)
    internal fun skimMobProperties(propertyName: String, unknownProperty: String, drop: String, properties: Collection<Any>): Component {
        return Component.text("$propertyName: ", NamedTextColor.GRAY)
            .append(Component.newline())
            .let { component ->

                // If the [properties] value is empty just don't do anything else.
                if (properties.isEmpty()) return@let component

                // Map all values to (key: value)
                return@let component.append(properties.map { target ->
                    // Drop the "drop" keyword's length from the target class's name (if the name doesnt exist use unknownProperty)
                    Component.text((target::class.simpleName?.dropLast(drop.length)) ?: unknownProperty, NamedTextColor.WHITE)
                        // Upcoming (key: value...) component
                        .append(Component.text(" (${
                            // Combine all the member properties of the target to (key: value...)
                            target::class.memberProperties.joinToString { value ->

                                // super hacky hack in order to trick java into thinking this is fine
                                @Suppress("UNCHECKED_CAST")
                                value as KProperty1<Any, *>

                                // Special case for any non-stringable classes.
                                val stringParser: (Any) -> String = when (value.get(target)!!::class) {
                                    UpdateOption::class -> { any -> 
                                        any as UpdateOption
                                        
                                        "${any.value} ${any.timeUnit}"
                                    }
                                    else -> { any -> any.toString() }
                                }

                                // Then return key: value)
                                "${value.name}: ${stringParser(value.get(target)!!)}"
                            }
                        })", NamedTextColor.GRAY))
                        .append(Component.newline())
                }.reduce { acc, textComponent -> acc.append(textComponent) })
            }
    }

    init {
        addSyntax { sender ->
            val player = sender as Player

            val mob = player.mob ?: return@addSyntax

            player.sendMessage(
                skimMobProperties("Meta", "Unknown Meta", "Meta", mob.properties.metas.values)
                    .append(Component.newline())
                    .append(skimMobProperties("Goals", "Unknown Goal", "Goal", mob.properties.goals))
                    .append(Component.newline())
                    .append(skimMobProperties("Targets", "Unknown Target", "Target", mob.properties.targets))
                    .append(Component.newline())
                    .append(Component.text("Type: ", NamedTextColor.GRAY)
                        .append(Component.text(EntityData.findByType(mob.properties.type)?.displayName ?: "Unknown", NamedTextColor.WHITE)))
            )

        }
    }

}