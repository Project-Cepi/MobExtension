package world.cepi.mobextension.commands

import net.minestom.server.command.builder.Command
import net.minestom.server.command.builder.arguments.ArgumentType
import net.minestom.server.entity.Player
import world.cepi.mobextension.MobExtension.Companion.dataDir
import world.cepi.mobextension.SerializableMob
import java.io.File

class MobCommand : Command("mob") {

    var files: List<File> = listOf()

    private fun refreshFiles(): List<File> {
        return dataDir.walk().filter { it.isFile }.toList()
    }

    init {

        dataDir.mkdirs()

        files = refreshFiles()

        val spawn = ArgumentType.Word("spawn").from("spawn")
        val reload = ArgumentType.Word("reload").from("reload")

        val mobFiles = ArgumentType.DynamicWord("mobs").fromRestrictions { value ->
            files.any { it.nameWithoutExtension == value }
        }

        setArgumentCallback({ commandSender, _, _ ->
            commandSender.sendMessage("Requires a proper file name!")
        }, mobFiles)

        addSyntax({ sender, args ->

            if (sender !is Player) return@addSyntax

            val fileName = args.getWord("mobs")
            val file = File(dataDir, "$fileName.json")
            val json = file.readText()
            val mob = SerializableMob.fromJSON(json).toMob()
            val creature = mob.generateMob(sender.position) ?: return@addSyntax
            creature.setInstance(sender.instance!!)
            creature.teleport(sender.position)
            creature.refreshPosition(sender.position)
        }, spawn, mobFiles)

        addSyntax({ sender, _ ->
            files = refreshFiles()
            sender.sendMessage("Refreshed mob files!")
        }, reload)
    }

    override fun onDynamicWrite(text: String): Array<String> {
        return files.map { it.nameWithoutExtension }.toTypedArray()
    }

}