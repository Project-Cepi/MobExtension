package world.cepi.mobextension.commands

import com.mattworzala.canvas.BlankProps
import com.mattworzala.canvas.Canvas
import com.mattworzala.canvas.CanvasProvider
import net.minestom.server.command.CommandSender
import net.minestom.server.command.builder.Command
import net.minestom.server.command.builder.arguments.ArgumentType
import net.minestom.server.entity.Player
import net.minestom.server.item.Material
import world.cepi.kepi.sendFormattedMessage
import world.cepi.kstom.command.addSyntax
import world.cepi.kstom.command.arguments.argumentsFromConstructor
import world.cepi.kstom.command.arguments.asSubcommand
import world.cepi.mobextension.EntityData
import world.cepi.mobextension.Mob
import world.cepi.mobextension.MobExtension.Companion.dataDir
import world.cepi.mobextension.SerializableMob
import world.cepi.mobextension.entityData
import world.cepi.mobextension.goal.GoalObjectCollection
import world.cepi.mobextension.meta.MetaObjectCollection
import world.cepi.mobextension.ui.MainScreen
import java.io.File
import kotlin.reflect.full.primaryConstructor

class MobCommand : Command("mob") {

    private var files: List<File> = listOf()

    private fun refreshFiles(): List<File> {
        return dataDir.walk().filter { it.isFile }.toList()
    }

    init {

        dataDir.mkdirs()

        files = refreshFiles()

        val ui = "ui".asSubcommand()

        val create = "create".asSubcommand()
        val meta = "meta".asSubcommand()
        val goals = "goals".asSubcommand()
        val targets = "targets".asSubcommand()
        val type = "type".asSubcommand()

        val set = "set".asSubcommand()
        val insert = "insert".asSubcommand()
        val add = "insert".asSubcommand()
        val remove = "remove".asSubcommand()

        val registry = "registry".asSubcommand()
        val spawn = "spawn".asSubcommand()
        val reload = "reload".asSubcommand()
        val get = "get".asSubcommand()

        val spawner = "spawner".asSubcommand()

        val amount = ArgumentType.Integer("amount").max(100).min(1)
        amount.defaultValue = 1

        val mobFiles = ArgumentType.DynamicWord("mobs").fromRestrictions { value ->
            files.any { it.nameWithoutExtension == value }
        }

        setArgumentCallback({ commandSender, _ ->
            commandSender.sendFormattedMessage(properFileName)
        }, mobFiles)

        addSyntax(ui) { sender ->
            if (sender !is Player) return@addSyntax

            if (sender.itemInMainHand.material == Material.AIR) {
                sender.sendFormattedMessage(mustHaveItemInHand)
                return@addSyntax
            }

            if (sender.itemInMainHand.entityData == null) {
                sender.sendFormattedMessage(mobSpawnEggInHand)
                return@addSyntax
            }

            val canvas: Canvas = CanvasProvider.canvas(sender)
            canvas.render(MainScreen, BlankProps);
        }

        addSyntax(create) { sender ->

            if (sender !is Player) return@addSyntax

            if (sender.itemInMainHand.material == Material.AIR) {
                sender.sendFormattedMessage(mustHaveItemInHand)
                return@addSyntax
            }

            if (sender.itemInMainHand.entityData == null) {
                sender.sendFormattedMessage(mobSpawnEggInHand)
                return@addSyntax
            }

            val mob = Mob(Mob.Properties().setType(sender.itemInMainHand.entityData!!.type))

            sender.itemInMainHand = mob.generateEgg()

        }

        addSyntax(spawn, amount) { sender, args ->
            if (sender !is Player) return@addSyntax

            if (sender.itemInMainHand.material == Material.AIR) {
                sender.sendFormattedMessage(mustHaveItemInHand)
                return@addSyntax
            }

            if (sender.itemInMainHand.data?.get<Mob>(Mob.mobKey) == null) {
                sender.sendFormattedMessage(mobSpawnEggInHand)
                return@addSyntax
            }

            val mob = sender.itemInMainHand.data?.get<Mob>(Mob.mobKey)!!

            repeat(args.get(amount)) {
                val creature = mob.generateMob(sender.position) ?: return@addSyntax
                creature.setInstance(sender.instance!!)
                creature.teleport(sender.position)
                creature.refreshPosition(sender.position)
            }
        }

        MetaObjectCollection.objects.forEach { clazz ->
            val arguments = argumentsFromConstructor(clazz.primaryConstructor!!)

            var clazzArgumentName = clazz.simpleName!!.toLowerCase()
            clazzArgumentName = clazzArgumentName.substring(0, clazzArgumentName.length - 4)

            // TODO repeating code

            addSyntax(meta, set, clazzArgumentName.asSubcommand(), *arguments.toTypedArray()) { sender, args ->
                if (sender !is Player) return@addSyntax

                if (sender.itemInMainHand.material == Material.AIR) {
                    sender.sendFormattedMessage(mustHaveItemInHand)
                    return@addSyntax
                }

                if (sender.itemInMainHand.data?.get<Mob>(Mob.mobKey) == null) {
                    sender.sendFormattedMessage(mobSpawnEggInHand)
                    return@addSyntax
                }

                val mob = sender.itemInMainHand.data?.get<Mob>(Mob.mobKey)!!

                val metaArg = clazz.primaryConstructor!!.call(*arguments.map { args.get(it) }.toTypedArray())

                mob.properties.addMeta(metaArg)

                sender.itemInMainHand = mob.generateEgg()
            }

            addSyntax(meta, remove, clazz.simpleName!!.asSubcommand()) { sender ->
                if (sender !is Player) return@addSyntax

                if (sender.itemInMainHand.material == Material.AIR) {
                    sender.sendFormattedMessage(mustHaveItemInHand)
                    return@addSyntax
                }

                if (sender.itemInMainHand.data?.get<Mob>(Mob.mobKey) == null) {
                    sender.sendFormattedMessage(mobSpawnEggInHand)
                    return@addSyntax
                }

                val mob = sender.itemInMainHand.data?.get<Mob>(Mob.mobKey)!!

                mob.properties.metas.removeIf { it == clazz }

                sender.itemInMainHand = mob.generateEgg()
            }

        }

        GoalObjectCollection.objects.forEach { clazz ->

            val arguments = argumentsFromConstructor(clazz.primaryConstructor!!)

            var clazzArgumentName = clazz.simpleName!!.toLowerCase()
            clazzArgumentName = clazzArgumentName.substring(0, clazzArgumentName.length - 4)

            addSyntax(goals, add, clazzArgumentName.asSubcommand(), *arguments.toTypedArray()) { sender, args ->
                if (sender !is Player) return@addSyntax

                if (sender.itemInMainHand.material == Material.AIR) {
                    sender.sendFormattedMessage(mustHaveItemInHand)
                    return@addSyntax
                }

                if (sender.itemInMainHand.data?.get<Mob>(Mob.mobKey) == null) {
                    sender.sendFormattedMessage(mobSpawnEggInHand)
                    return@addSyntax
                }

                val mob = sender.itemInMainHand.data?.get<Mob>(Mob.mobKey)!!

                val goalArg = clazz.primaryConstructor!!.call(*arguments.map { args.get(it) }.toTypedArray())

                mob.properties.addGoal(goalArg)

                sender.itemInMainHand = mob.generateEgg()
            }

        }

        EntityData.mobTypeList.forEach {

            val materialType = it.material.name.toLowerCase()
            val typeArg = materialType.dropLast("_SPAWN_EGG".length).asSubcommand()

            addSyntax(type, typeArg) { sender ->
                if (sender !is Player) return@addSyntax

                if (sender.itemInMainHand.material == Material.AIR) {
                    sender.sendFormattedMessage(mustHaveItemInHand)
                    return@addSyntax
                }

                if (sender.itemInMainHand.data?.get<Mob>(Mob.mobKey) == null) {
                    sender.sendFormattedMessage(mobSpawnEggInHand)
                    return@addSyntax
                }

                val mob = sender.itemInMainHand.data?.get<Mob>(Mob.mobKey)!!

                mob.properties.setType(it.type)

                sender.itemInMainHand = mob.generateEgg()
            }
        }

        addSyntax(registry, spawn, mobFiles, amount) { sender, args ->

            if (sender !is Player) return@addSyntax

            val fileName = args.get(mobFiles)
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

            val fileName = args.get(mobFiles)
            val file = File(dataDir, "$fileName.json")
            val mob = SerializableMob.fromJSON(file.readText()).toMob()

            sender.inventory.addItemStack(mob.generateEgg())
        }

        addSyntax(registry, reload) { sender ->
            files = refreshFiles()
            sender.sendFormattedMessage(refreshedMobFiles)
        }
    }

    override fun onDynamicWrite(sender: CommandSender, text: String): Array<out String> {
        return files.map { it.nameWithoutExtension }.toTypedArray()
    }

}