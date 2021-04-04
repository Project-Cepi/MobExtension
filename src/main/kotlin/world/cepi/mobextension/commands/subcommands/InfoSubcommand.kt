package world.cepi.mobextension.commands.subcommands

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import net.minestom.server.command.builder.Command
import net.minestom.server.entity.Player
import world.cepi.kstom.command.addSyntax
import world.cepi.mobextension.mob
import kotlin.reflect.KProperty1
import kotlin.reflect.full.declaredMemberProperties
import kotlin.reflect.full.memberProperties

internal object InfoSubcommand : Command("info") {

    init {
        addSyntax { sender ->
            val player = sender as Player

            val mob = player.mob ?: return@addSyntax

            // TODO DRY refactoring

            player.sendMessage(
                Component.text("Meta: ", NamedTextColor.GRAY)
                    .append(Component.newline())
                    .let { component ->
                        component.append(mob.properties.metas.map {
                            Component.text(it::class.simpleName ?: "Unknown Meta", NamedTextColor.WHITE)
                                .append(Component.text(" (${it.value()})", NamedTextColor.GRAY))
                                .append(Component.newline())
                        }.reduce { acc, textComponent -> acc.append(textComponent) })
                    }
                    .append(Component.newline())
                    .append(Component.text("Goals: ", NamedTextColor.GRAY)
                        .append(Component.newline())
                        .let { component ->
                            component.append(mob.properties.goals.map { goal ->
                                Component.text(goal::class.simpleName ?: "Unknown Goal", NamedTextColor.WHITE)
                                    .append(Component.text(" (${
                                        goal::class.memberProperties.joinToString { value ->

                                            @Suppress("UNCHECKED_CAST")
                                            value as KProperty1<Any, *> // super hacky hack in order to trick java into thinking this is fine

                                            "${value.name}: ${value.get(goal).toString()}"
                                        }
                                    })", NamedTextColor.GRAY))
                                    .append(Component.newline())
                            }.reduce { acc, textComponent -> acc.append(textComponent) })
                        })
                    .append(Component.newline())
                    .append(Component.text("Targets: ", NamedTextColor.GRAY)
                        .append(Component.newline())
                        .let { component ->
                            component.append(mob.properties.targets.map { target ->
                                Component.text(target::class.simpleName ?: "Unknown Target", NamedTextColor.WHITE)
                                    .append(Component.text(" (${
                                        target::class.memberProperties.joinToString { value ->

                                            @Suppress("UNCHECKED_CAST")
                                            value as KProperty1<Any, *> // super hacky hack in order to trick java into thinking this is fine

                                            "${value.name}: ${value.get(target).toString()}"
                                        }
                                    })", NamedTextColor.GRAY))
                                    .append(Component.newline())
                            }.reduce { acc, textComponent -> acc.append(textComponent) })
                        })
            )

        }
    }

}