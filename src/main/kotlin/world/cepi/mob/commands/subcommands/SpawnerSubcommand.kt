package world.cepi.mob.commands.subcommands

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.event.ClickEvent
import net.kyori.adventure.text.event.HoverEvent
import net.kyori.adventure.text.format.NamedTextColor
import net.minestom.server.command.builder.Command
import net.minestom.server.command.builder.arguments.ArgumentType
import net.minestom.server.command.builder.exception.ArgumentSyntaxException
import net.minestom.server.entity.Player
import world.cepi.kepi.messages.sendFormattedTranslatableMessage
import world.cepi.kstom.command.addSyntax
import world.cepi.kstom.command.arguments.literal
import world.cepi.kstom.command.arguments.suggest
import world.cepi.mob.commands.*
import world.cepi.mob.mob.mobEgg
import world.cepi.mob.spawner.MobSpawner
import world.cepi.mob.util.MobUtils

internal object SpawnerSubcommand : Command("spawner") {

    init {

        val create = "create".literal()

        val newName = ArgumentType.String("name")
        val existingName = ArgumentType.Word("name").map {
            MobSpawner.getSpawner(it) ?: throw ArgumentSyntaxException("Lootcrate not found", it, 1)
        }.suggest {
            MobSpawner.spawners.keys.toList()
        }.also {
            it.setCallback { commandSender, argumentSyntaxException ->
                commandSender.sendFormattedTranslatableMessage(
                    "mob",
                    "spawner.none",
                    Component.text(argumentSyntaxException.input, NamedTextColor.BLUE)
                )
            }
        }
        val list = "list".literal()

        val limit = "limit".literal()
        val limitAmount = ArgumentType.Integer("limitAmount").min(1).max(100)

        val time = "time".literal()
        val timeAmount = ArgumentType.Time("timeAmount")

        val remove = "remove".literal()

        val locations = "locations".literal()
        val add = "add".literal()

        addSyntax(create, newName) {
            if (!MobUtils.hasMobEgg(sender)) return@addSyntax

            val player = sender as Player

            val mob = player.mobEgg ?: return@addSyntax

            MobSpawner.createSpawner(
                context.get(newName),
                MobSpawner(context.get(newName), player.instance!!, mutableListOf(player.position.toBlockPosition()), mob)
            )

            player.sendFormattedTranslatableMessage("mob", "create", Component.text(context.get(newName)))
        }

        addSyntax(remove, existingName) {
            val player = sender as Player

            MobSpawner.removeSpawner(context.get(existingName).id)

            player.sendFormattedTranslatableMessage(
                "mob",
                "spawner.delete",
                Component.text(context.get(existingName).id, NamedTextColor.BLUE)
            )
        }

        addSyntax(limit, limitAmount, existingName) {

            val runtimeSpawner = context.get(existingName)

            runtimeSpawner.limit = context.get(limitAmount)

            sender.sendFormattedTranslatableMessage(
                "mob",
                "spawner.limit.set",
                Component.text(runtimeSpawner.id, NamedTextColor.BLUE),
                Component.text(context.get(limitAmount), NamedTextColor.YELLOW)
            )

        }

        addSyntax(time, timeAmount, existingName) {

            val runtimeSpawner = context.get(existingName)

            runtimeSpawner.spawnOption = context.get(timeAmount)

            sender.sendFormattedTranslatableMessage(
                "mob",
                "spawner.speed.set",
                Component.text(context.get(existingName).id, NamedTextColor.BLUE),
                Component.text(context.get(timeAmount).value, NamedTextColor.YELLOW)
            )

        }

        addSyntax(list) {
            sender.sendMessage(Component.text("(", NamedTextColor.GRAY)
                .append(Component.text(MobSpawner.amount(), NamedTextColor.WHITE))
                .append(Component.text(")", NamedTextColor.GRAY))
                .append(Component.space())
                .append(Component.text(MobSpawner.spawners.keys.joinToString(), NamedTextColor.WHITE))
            )
        }

        addSyntax(locations, add, existingName) {

            val player = sender as Player

            val runtimeSpawner = MobSpawner.getSpawner(context.get(name))!!

            val position = player.position.toBlockPosition()

            runtimeSpawner.viablePositions.add(position)

            player.sendFormattedTranslatableMessage(
                "mob",
                "spawner.position.add",
                Component.text("${position.x} ${position.y} ${position.z}")
                    .hoverEvent(HoverEvent.showText(Component.text(clickToTeleport, NamedTextColor.GRAY)))
                    .clickEvent(ClickEvent.runCommand("/tp ${position.x} ${position.y} ${position.z}"))
            )
        }

        addSyntax(locations, remove, existingName) {
            val player = sender as Player

            val runtimeSpawner = MobSpawner.getSpawner(context.get(name))!!

            val position = player.position.toBlockPosition()

            val nearestPosition = runtimeSpawner.viablePositions.sortedBy { it.getDistance(position) }[0]
            runtimeSpawner.viablePositions.remove(nearestPosition)

            player.sendFormattedTranslatableMessage(
                "mob",
                "spawner.position.remove",
                Component.text("${nearestPosition.x} ${nearestPosition.y} ${nearestPosition.z}")
                    .hoverEvent(HoverEvent.showText(Component.text(clickToTeleport, NamedTextColor.GRAY)))
                    .clickEvent(ClickEvent.runCommand("/tp ${nearestPosition.x} ${nearestPosition.y} ${nearestPosition.z}"))
            )
        }

        addSyntax(locations, list, existingName) {
            val runtimeSpawner = context.get(existingName)

            sender.sendMessage(Component.text("(", NamedTextColor.GRAY)
                .append(Component.text(MobSpawner.amount(), NamedTextColor.WHITE))
                .append(Component.text(")", NamedTextColor.GRAY))
                .append(Component.space())
                .let {
                    return@let it.append(runtimeSpawner.viablePositions
                        .mapIndexed { index, position ->
                            with(position) {
                                return@mapIndexed (
                                        if (index != 0)
                                            Component.text(", ", NamedTextColor.DARK_GRAY)
                                        else
                                            Component.empty()
                                        )
                                    .append(Component.text("$x $y $z"))
                                    .hoverEvent(HoverEvent.showText(Component.text(clickToTeleport, NamedTextColor.GRAY)))
                                    .clickEvent(ClickEvent.runCommand("/tp $x $y $z"))
                            }
                        }
                        .reduce { acc, component -> acc.append(component) })
                }
            )
        }
    }

}