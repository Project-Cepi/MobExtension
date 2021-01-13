package world.cepi.mobextension.commands

import net.minestom.server.command.CommandSender
import net.minestom.server.command.builder.Command
import net.minestom.server.command.builder.arguments.ArgumentType
import net.minestom.server.entity.Player
import world.cepi.kstom.addSyntax
import world.cepi.kstom.arguments.asSubcommand
import world.cepi.mobextension.MobExtension.Companion.dataDir
import world.cepi.mobextension.SerializableMob
import java.io.File

class MobCommand : Command("mob") {

    private var files: List<File> = listOf()

    private fun refreshFiles(): List<File> {
        return dataDir.walk().filter { it.isFile }.toList()
    }

    init {

        dataDir.mkdirs()

        files = refreshFiles()


        val create = "create".asSubcommand()

        val registry = "registry".asSubcommand()
        val spawn = "spawn".asSubcommand()
        val reload = "reload".asSubcommand()


        val amount = ArgumentType.Integer("amount").max(10).min(1)
        amount.defaultValue = 1

        val mobFiles = ArgumentType.DynamicWord("mobs").fromRestrictions { value ->
            files.any { it.nameWithoutExtension == value }
        }

        setArgumentCallback({ commandSender, _ ->
            commandSender.sendMessage("Requires a proper file name!")
        }, mobFiles)

        addSyntax(registry, spawn, mobFiles, amount) { sender, args ->

            if (sender !is Player) return@addSyntax

            val fileName = args.getWord("mobs")
            val file = File(dataDir, "$fileName.json")
            val json = file.readText()

            repeat(args.get(amount)) {
                val mob = SerializableMob.fromJSON(json).toMob()
                val creature = mob.generateMob(sender.position) ?: return@addSyntax
                creature.setInstance(sender.instance!!)
                creature.teleport(sender.position)
                creature.refreshPosition(sender.position)
            }
        }

        addSyntax(registry, reload) { sender ->
            files = refreshFiles()
            sender.sendMessage("Refreshed mob files!")
        }
    }

    override fun onDynamicWrite(sender: CommandSender, text: String): Array<out String> {
        return files.map { it.nameWithoutExtension }.toTypedArray()
    }

}