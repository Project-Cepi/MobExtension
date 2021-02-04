package world.cepi.mobextension.commands

import net.minestom.server.command.CommandSender
import net.minestom.server.command.builder.Command
import net.minestom.server.command.builder.arguments.ArgumentType
import net.minestom.server.entity.Player
import net.minestom.server.item.Material
import world.cepi.kstom.addSyntax
import world.cepi.kstom.arguments.asSubcommand
import world.cepi.mobextension.Mob
import world.cepi.mobextension.MobExtension.Companion.dataDir
import world.cepi.mobextension.SerializableMob
import world.cepi.mobextension.entityData
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
        val meta = "meta".asSubcommand()

        val set = "set".asSubcommand()
        val remove = "remove".asSubcommand()

        val goals = "goals".asSubcommand()
        val targets = "targets".asSubcommand()

        val push = "push".asSubcommand() // Add to end of array
        val pop = "pop".asSubcommand() // Remove from the end of the array
        val shift = "shift".asSubcommand() // Remove from beginning of array
        val unshift = "unshift".asSubcommand() // Add to beginning of array
        val splice = "splice".asSubcommand() // Removes all elements starting with X index.

        val registry = "registry".asSubcommand()
        val spawn = "spawn".asSubcommand()
        val reload = "reload".asSubcommand()
        val get = "get".asSubcommand()

        val amount = ArgumentType.Integer("amount").max(100).min(1)
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

            if (sender.itemInMainHand.entityData == null) {
                sender.sendMessage("You must have a mob spawn egg in your hand!")
                return@addSyntax
            }

            val mob = Mob(Mob.Properties().setType(sender.itemInMainHand.entityData!!.type))

            sender.itemInMainHand = mob.generateEgg()

        }

        addSyntax(registry, spawn, mobFiles, amount) { sender, args ->

            if (sender !is Player) return@addSyntax

            val fileName = args.getWord("mobs")
            val file = File(dataDir, "$fileName.json")
            val json = file.readText()

            val mob = SerializableMob.fromJSON(json).toMob()

            repeat(args.get(amount)) {
                val creature = mob.generateMob(sender.position) ?: return@addSyntax
                creature.setInstance(sender.instance!!)
                creature.teleport(sender.position)
                creature.refreshPosition(sender.position)
            }
        }

        addSyntax(registry, get, mobFiles) { sender, args ->

            if (sender !is Player) return@addSyntax

            val fileName = args.getWord("mobs")
            val file = File(dataDir, "$fileName.json")
            val json = file.readText()

            val mob = SerializableMob.fromJSON(json).toMob()

            sender.inventory.addItemStack(mob.generateEgg())
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