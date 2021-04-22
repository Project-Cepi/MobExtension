package world.cepi.mobextension.commands.subcommands

import net.kyori.adventure.text.Component
import net.minestom.server.command.builder.Command
import net.minestom.server.command.builder.arguments.ArgumentType
import net.minestom.server.entity.Player
import world.cepi.kepi.messages.sendFormattedMessage
import world.cepi.kstom.command.addSyntax
import world.cepi.kstom.command.arguments.asSubcommand
import world.cepi.mobextension.MobExtension
import world.cepi.mobextension.SerializableMob
import world.cepi.mobextension.commands.MobCommand
import world.cepi.mobextension.commands.properFileName
import world.cepi.mobextension.commands.refreshedMobFiles
import java.io.File
import java.util.function.Supplier

internal object RegistrySubcommand : Command("registry") {

    init {

        val spawn = "spawn".asSubcommand()
        val reload = "reload".asSubcommand()
        val get = "get".asSubcommand()

        val amount = ArgumentType.Integer("amount").max(100).min(1)
        amount.defaultValue = Supplier { 1 }

        val mobFiles = ArgumentType.DynamicWord("mobs").fromRestrictions { value ->
            MobCommand.files.any { it.nameWithoutExtension == value }
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