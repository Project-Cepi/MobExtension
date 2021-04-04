package world.cepi.mobextension.commands.subcommands

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import net.minestom.server.command.builder.Command
import net.minestom.server.entity.Player
import world.cepi.kstom.command.addSyntax
import world.cepi.mobextension.EntityData
import world.cepi.mobextension.mob
import kotlin.reflect.KProperty1
import kotlin.reflect.full.memberProperties

internal object InfoSubcommand : Command("info") {

    private fun skimMobProperties(propertyName: String, unknownProperty: String, drop: String, properties: Collection<Any>): Component {
        return Component.text("$propertyName: ", NamedTextColor.GRAY)
            .append(Component.newline())
            .let { component ->
                component.append(properties.map { target ->
                    Component.text((target::class.simpleName?.dropLast(drop.length)) ?: unknownProperty, NamedTextColor.WHITE)
                        .append(Component.text(" (${
                            target::class.memberProperties.joinToString { value ->

                                @Suppress("UNCHECKED_CAST")
                                value as KProperty1<Any, *> // super hacky hack in order to trick java into thinking this is fine

                                "${value.name}: ${value.get(target).toString()}"
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