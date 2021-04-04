package world.cepi.mobextension.commands.subcommands

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.event.ClickEvent
import net.kyori.adventure.text.event.HoverEvent
import net.kyori.adventure.text.format.NamedTextColor
import net.minestom.server.command.builder.Command
import net.minestom.server.command.builder.arguments.ArgumentType
import net.minestom.server.entity.Player
import world.cepi.kepi.messages.sendFormattedMessage
import world.cepi.kstom.command.addSyntax
import world.cepi.kstom.command.arguments.asSubcommand
import world.cepi.mobextension.commands.*
import world.cepi.mobextension.mob
import world.cepi.mobextension.spawner.MobSpawner

internal object SpawnerSubcommand : Command("spawner") {

    init {

        val create = "create".asSubcommand()

        val name = ArgumentType.String("name")
        val list = "list".asSubcommand()

        val limit = "limit".asSubcommand()
        val limitAmount = ArgumentType.Integer("limitAmount").min(1).max(100)

        val tick = "tick".asSubcommand()
        val tickAmount = ArgumentType.Integer("tickAmount").min(1)

        val remove = "remove".asSubcommand()

        val locations = "locations".asSubcommand()
        val add = "add".asSubcommand()

        addSyntax(create, name) { sender, args ->
            if (!MobCommand.hasMobEgg(sender)) return@addSyntax

            val player = sender as Player

            val mob = player.mob ?: return@addSyntax

            MobSpawner.createSpawner(args.get(name), MobSpawner(player.instance!!, mutableListOf(player.position.toBlockPosition()), mob))

            player.sendFormattedMessage(Component.text(mobSpawnerCreated), Component.text(args.get(name)))
        }

        addSyntax(remove, name) { sender, args ->
            val player = sender as Player

            MobSpawner.removeSpawner(args.get(name))

            player.sendFormattedMessage(mobSpawnerDeleted)
        }

        addSyntax(limit, limitAmount, name) { sender, args ->

            val runtimeSpawner = MobSpawner.getSpawner(args.get(name))

            if (runtimeSpawner == null) {
                sender.sendFormattedMessage(Component.text(mobSpawnerNotFound))
                return@addSyntax
            }

            runtimeSpawner.limit = args.get(limitAmount)

            sender.sendFormattedMessage(Component.text(mobSpawnerLimit), Component.text(args.get(name)), Component.text(args.get(limitAmount).toString()))

        }

        addSyntax(tick, tickAmount, name) { sender, args ->

            val runtimeSpawner = MobSpawner.getSpawner(args.get(name))

            if (runtimeSpawner == null) {
                sender.sendFormattedMessage(Component.text(mobSpawnerNotFound))
                return@addSyntax
            }

            runtimeSpawner.ticksPerSpawn = args.get(tickAmount)

            sender.sendFormattedMessage(Component.text(mobSpawnerTickSpeed), Component.text(args.get(name)), Component.text(args.get(tickAmount).toString()))

        }

        addSyntax(list) { sender ->
            sender.sendMessage(Component.text("(", NamedTextColor.GRAY)
                .append(Component.text(MobSpawner.amount(), NamedTextColor.WHITE))
                .append(Component.text(")", NamedTextColor.GRAY))
                .append(Component.space())
                .append(Component.text(MobSpawner.spawners.keys.joinToString(), NamedTextColor.WHITE))
            )
        }

        addSyntax(locations, add, name) { sender, args ->

            val player = sender as Player

            val runtimeSpawner = MobSpawner.getSpawner(args.get(name))!!

            val position = player.position.toBlockPosition()

            runtimeSpawner.viablePositions.add(position)

            player.sendFormattedMessage(
                mobSpawnerPositionAdded,
                Component.text("${position.x} ${position.y} ${position.z}")
                    .hoverEvent(HoverEvent.showText(Component.text(clickToTeleport, NamedTextColor.GRAY)))
                    .clickEvent(ClickEvent.runCommand("/tp ${position.x} ${position.y} ${position.z}"))
            )
        }

        addSyntax(locations, remove, name) { sender, args ->
            val player = sender as Player

            val runtimeSpawner = MobSpawner.getSpawner(args.get(name))!!

            val position = player.position.toBlockPosition()

            val nearestPosition = runtimeSpawner.viablePositions.sortedBy { it.getDistance(position) }[0]
            runtimeSpawner.viablePositions.remove(nearestPosition)

            player.sendFormattedMessage(
                mobSpawnerPositionRemoved,
                Component.text("${nearestPosition.x} ${nearestPosition.y} ${nearestPosition.z}")
                    .hoverEvent(HoverEvent.showText(Component.text(clickToTeleport, NamedTextColor.GRAY)))
                    .clickEvent(ClickEvent.runCommand("/tp ${nearestPosition.x} ${nearestPosition.y} ${nearestPosition.z}"))
            )
        }

        addSyntax(locations, list, name) { sender, args ->
            val runtimeSpawner = MobSpawner.getSpawner(args.get(name))!!

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