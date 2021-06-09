package world.cepi.mob.commands.subcommands

import net.kyori.adventure.text.Component
import net.minestom.server.command.builder.Command
import net.minestom.server.command.builder.arguments.ArgumentType
import net.minestom.server.command.builder.exception.ArgumentSyntaxException
import net.minestom.server.entity.Player
import world.cepi.kepi.messages.sendFormattedMessage
import world.cepi.kstom.command.addSyntax
import world.cepi.kstom.command.arguments.literal
import world.cepi.mob.MobExtension
import world.cepi.mob.commands.MobCommand
import world.cepi.mob.commands.properFileName
import world.cepi.mob.commands.refreshedMobFiles
import world.cepi.mob.mob.SerializableMob
import java.io.File
import java.util.function.Supplier

internal object RegistrySubcommand : Command("registry") {

    init {

        val spawn = "spawn".literal()
        val reload = "reload".literal()
        val get = "get".literal()

        val amount = ArgumentType.Integer("amount").max(100).min(1)
        amount.defaultValue = Supplier { 1 }

        // TODO use data storage
        val mobFiles = ArgumentType.Word("mobs").map { value ->
            if (!MobCommand.files.any { it.nameWithoutExtension == value })
                throw ArgumentSyntaxException("Mob file not found", value, 1)

            value
        }

        MobCommand.setArgumentCallback({ commandSender, _ ->
            commandSender.sendFormattedMessage(Component.text(properFileName))
        }, mobFiles)

        addSyntax(spawn, mobFiles, amount) { sender, args ->

            if (sender !is Player) return@addSyntax

            val fileName = args.get(mobFiles)
            val file = File(MobExtension.dataDir, "$fileName.json")
            val json = file.readText()

            val mob = SerializableMob.fromJSON(json).toMob()

            repeat(args.get(amount)) {
                val creature = mob.generateMob() ?: return@addSyntax
                creature.setInstance(sender.instance!!, sender.position)
            }
        }

        addSyntax(get, mobFiles) { sender, args ->

            if (sender !is Player) return@addSyntax

            val fileName = args.get(mobFiles)
            val file = File(MobExtension.dataDir, "$fileName.json")
            val mob = SerializableMob.fromJSON(file.readText()).toMob()

            sender.inventory.addItemStack(mob.generateEgg())
        }

        addSyntax(reload) { sender ->
            MobCommand.files = MobCommand.refreshFiles()
            sender.sendFormattedMessage(Component.text(refreshedMobFiles))
        }
    }

}