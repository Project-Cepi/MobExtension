package world.cepi.mobextension.commands.subcommands

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import net.minestom.server.command.builder.Command
import net.minestom.server.command.builder.arguments.ArgumentType
import net.minestom.server.entity.Player
import world.cepi.kepi.messages.sendFormattedMessage
import world.cepi.kstom.command.addSyntax
import world.cepi.kstom.command.arguments.asSubcommand
import world.cepi.mobextension.Mob
import world.cepi.mobextension.commands.*
import world.cepi.mobextension.mob
import world.cepi.mobextension.spawner.MobSpawner

object SpawnerSubcommand : Command("spawner") {

    init {

        val create = "create".asSubcommand()

        val name = ArgumentType.String("name")
        val list = "list".asSubcommand()

        val limit = "limit".asSubcommand()
        val limitAmount = ArgumentType.Integer("limitAmount").min(1).max(100)

        val tick = "tick".asSubcommand()
        val tickAmount = ArgumentType.Integer("tickAmount").min(1)

        val remove = "remove".asSubcommand()

        addSyntax(create, name) { sender, args ->
            if (!MobCommand.hasMobEgg(sender)) return@addSyntax

            val player = sender as Player

            val mob = player.mob ?: return@addSyntax

            MobSpawner.createSpawner(args.get(name), MobSpawner(player.instance!!, listOf(player.position.toBlockPosition()), mob))

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
    }

}