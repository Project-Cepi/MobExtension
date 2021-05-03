package world.cepi.mobextension.commands.subcommands

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.event.ClickEvent
import net.kyori.adventure.text.event.HoverEvent
import net.kyori.adventure.text.format.NamedTextColor
import net.minestom.server.command.builder.Command
import net.minestom.server.command.builder.arguments.ArgumentType
import net.minestom.server.entity.Player
import world.cepi.kepi.messages.sendFormattedTranslatableMessage
import world.cepi.kstom.command.addSyntax
import world.cepi.kstom.command.arguments.literal
import world.cepi.mobextension.commands.*
import world.cepi.mobextension.mob
import world.cepi.mobextension.spawner.MobSpawner

internal object SpawnerSubcommand : Command("spawner") {

    init {

        val create = "create".literal()

        val name = ArgumentType.String("name")
        val list = "list".literal()

        val limit = "limit".literal()
        val limitAmount = ArgumentType.Integer("limitAmount").min(1).max(100)

        val time = "time".literal()
        val timeAmount = ArgumentType.Time("timeAmount")

        val remove = "remove".literal()

        val locations = "locations".literal()
        val add = "add".literal()

        addSyntax(create, name) { sender, args ->
            if (!MobCommand.hasMobEgg(sender)) return@addSyntax

            val player = sender as Player

            val mob = player.mob ?: return@addSyntax

            MobSpawner.createSpawner(args.get(name), MobSpawner(player.instance!!, mutableListOf(player.position.toBlockPosition()), mob))

            player.sendFormattedTranslatableMessage("mob", "create", Component.text(args.get(name)))
        }

        addSyntax(remove, name) { sender, args ->
            val player = sender as Player

            MobSpawner.removeSpawner(args.get(name))

            player.sendFormattedTranslatableMessage("mob", "spawner.delete", Component.text(args.get(name), NamedTextColor.BLUE))
        }

        addSyntax(limit, limitAmount, name) { sender, args ->

            val runtimeSpawner = MobSpawner.getSpawner(args.get(name))

            if (runtimeSpawner == null) {
                sender.sendFormattedTranslatableMessage("mob", "spawner.none", Component.text(args.get(name), NamedTextColor.BLUE))
                return@addSyntax
            }

            runtimeSpawner.limit = args.get(limitAmount)

            sender.sendFormattedTranslatableMessage(
                "mob",
                "spawner.limit.set",
                Component.text(args.get(name), NamedTextColor.BLUE),
                Component.text(args.get(limitAmount), NamedTextColor.YELLOW)
            )

        }

        addSyntax(time, timeAmount, name) { sender, args ->

            val runtimeSpawner = MobSpawner.getSpawner(args.get(name))

            if (runtimeSpawner == null) {
                sender.sendFormattedTranslatableMessage("mob", "spawner.none", Component.text(args.get(name), NamedTextColor.BLUE))
                return@addSyntax
            }

            runtimeSpawner.spawnOption = args.get(timeAmount)

            sender.sendFormattedTranslatableMessage(
                "mob",
                "spawner.speed.set",
                Component.text(args.get(name), NamedTextColor.BLUE),
                Component.text(args.get(timeAmount).value, NamedTextColor.YELLOW)
            )

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

            player.sendFormattedTranslatableMessage(
                "mob",
                "spawner.position.add",
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

            player.sendFormattedTranslatableMessage(
                "mob",
                "spawner.position.remove",
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