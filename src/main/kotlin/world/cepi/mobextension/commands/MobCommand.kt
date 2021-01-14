package world.cepi.mobextension.commands

import net.minestom.server.command.CommandSender
import net.minestom.server.command.builder.Command
import net.minestom.server.command.builder.arguments.ArgumentType
import net.minestom.server.data.DataImpl
import net.minestom.server.entity.Player
import net.minestom.server.item.ItemStack
import net.minestom.server.item.Material
import world.cepi.kstom.addSyntax
import world.cepi.kstom.arguments.asSubcommand
import world.cepi.mobextension.Mob
import world.cepi.mobextension.MobExtension.Companion.dataDir
import world.cepi.mobextension.SerializableMob
import world.cepi.mobextension.mobType
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

        addSyntax(create) { sender ->

            if (sender !is Player) return@addSyntax

            if (sender.itemInMainHand.material == Material.AIR) {
                sender.sendMessage("You must have an item in your hand!")
                return@addSyntax
            }

            if (sender.itemInMainHand.mobType == null) {
                sender.sendMessage("You must have a mob spawn egg in your hand!")
                return@addSyntax
            }

            val mob = Mob(Mob.Properties().setType(sender.itemInMainHand.mobType!!))
            val item = ItemStack(sender.itemInMainHand.material, 1)

            if (item.data == null) {
                item.data = DataImpl()
            }

            item.data!!.set(Mob.mobKey, mob)

        }

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