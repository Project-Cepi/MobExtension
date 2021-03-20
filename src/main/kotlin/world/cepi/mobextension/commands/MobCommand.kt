package world.cepi.mobextension.commands

import com.mattworzala.canvas.BlankProps
import com.mattworzala.canvas.Canvas
import com.mattworzala.canvas.CanvasProvider
import net.kyori.adventure.text.Component
import net.minestom.server.command.CommandSender
import net.minestom.server.command.builder.Command
import net.minestom.server.command.builder.arguments.ArgumentType
import net.minestom.server.entity.Player
import net.minestom.server.item.Material
import world.cepi.kepi.messages.sendFormattedMessage
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
import world.cepi.mobextension.spawner.MobSpawner
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
        val add = "add".asSubcommand()
        val remove = "remove".asSubcommand()

        val registry = "registry".asSubcommand()
        val spawn = "spawn".asSubcommand()
        val reload = "reload".asSubcommand()
        val get = "get".asSubcommand()

        val spawner = "spawner".asSubcommand()
        val name = ArgumentType.String("name")

        val limit = "limit".asSubcommand()
        val tick = "tick".asSubcommand()
        val limitAmount = ArgumentType.Integer("limitAmount").min(1).max(100)
        val tickAmount = ArgumentType.Integer("tickAmount").min(1)

        val amount = ArgumentType.Integer("amount").max(100).min(1)
        amount.defaultValue = 1

        val mobFiles = ArgumentType.DynamicWord("mobs").fromRestrictions { value ->
            files.any { it.nameWithoutExtension == value }
        }

        setArgumentCallback({ commandSender, _ ->
            commandSender.sendFormattedMessage(Component.text(properFileName))
        }, mobFiles)

        addSyntax(ui) { sender ->
            if (!hasMobEgg(sender)) return@addSyntax

            val player = sender as Player

            val canvas: Canvas = CanvasProvider.canvas(player)
            canvas.render(MainScreen, BlankProps);
        }

        addSyntax(create) { sender ->

            if (!hasMobEgg(sender)) return@addSyntax

            val player = sender as Player

            val mob = Mob(Mob.Properties().setType(player.itemInMainHand.entityData!!.type))

            player.itemInMainHand = mob.generateEgg()

            player.sendFormattedMessage(Component.text(mobCreated))

        }

        addSyntax(spawn, amount) { sender, context ->
            if (!hasMobEgg(sender)) return@addSyntax

            val player = sender as Player

            val mob = player.itemInMainHand.data?.get<Mob>(Mob.mobKey)!!

            repeat(context.get(amount)) {
                val creature = mob.generateMob() ?: return@addSyntax
                creature.setInstance(player.instance!!, player.position)
            }
        }

        MetaObjectCollection.objects.forEach { clazz ->
            val arguments = argumentsFromConstructor(clazz.primaryConstructor!!)

            val clazzArgumentName = clazz.simpleName!!.toLowerCase().dropLast(4)

            // TODO repeating code

            addSyntax(meta, set, clazzArgumentName.asSubcommand(), *arguments.toTypedArray()) { sender, args ->
                if (!hasMobEgg(sender)) return@addSyntax

                val player = sender as Player

                val mob = player.itemInMainHand.data?.get<Mob>(Mob.mobKey)!!

                val metaArg = clazz.primaryConstructor!!.call(*arguments.map { args.get(it) }.toTypedArray())

                mob.properties.addMeta(metaArg)

                player.itemInMainHand = mob.generateEgg()
            }

            addSyntax(meta, remove, clazzArgumentName.asSubcommand()) { sender ->
                if (!hasMobEgg(sender)) return@addSyntax

                val player = sender as Player

                val mob = player.itemInMainHand.data?.get<Mob>(Mob.mobKey)!!

                if (mob.properties.metas.removeIf { it == clazz }) {

                    player.itemInMainHand = mob.generateEgg()

                    player.sendFormattedMessage(Component.text(mobMetaSet), Component.text(clazzArgumentName))
                } else {

                } // TODO
            }

        }

        GoalObjectCollection.objects.forEach { clazz ->

            val arguments = argumentsFromConstructor(clazz.primaryConstructor!!)

            var clazzArgumentName = clazz.simpleName!!.toLowerCase()
            clazzArgumentName = clazzArgumentName.substring(0, clazzArgumentName.length - 4)

            addSyntax(goals, add, clazzArgumentName.asSubcommand(), *arguments.toTypedArray()) { sender, args ->
                if (!hasMobEgg(sender)) return@addSyntax

                val player = sender as Player

                val mob = player.itemInMainHand.data?.get<Mob>(Mob.mobKey)!!

                val goalArg = clazz.primaryConstructor!!.call(*arguments.map { args.get(it) }.toTypedArray())

                mob.properties.addGoal(goalArg)

                player.itemInMainHand = mob.generateEgg()

                player.sendFormattedMessage(Component.text(mobGoalSet), Component.text(clazzArgumentName))
            }

        }

        EntityData.mobTypeList.forEach {

            val typeArg = it.type.name.toLowerCase().asSubcommand()

            addSyntax(type, typeArg) { sender ->
                if (!hasMobEgg(sender)) return@addSyntax

                val player = sender as Player

                val mob = player.itemInMainHand.data?.get<Mob>(Mob.mobKey)!!

                mob.properties.setType(it.type)

                player.itemInMainHand = mob.generateEgg()
            }
        }


        addSyntax(registry, spawn, mobFiles, amount) { sender, args ->

            if (sender !is Player) return@addSyntax

            val fileName = args.get(mobFiles)
            val file = File(dataDir, "$fileName.json")
            val json = file.readText()

            val mob = SerializableMob.fromJSON(json).toMob()

            repeat(args.get(amount)) {
                val creature = mob.generateMob() ?: return@addSyntax
                creature.setInstance(sender.instance!!, sender.position)
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
            sender.sendFormattedMessage(Component.text(refreshedMobFiles))
        }

        addSyntax(spawner, create, name) { sender, args ->
            if (!hasMobEgg(sender)) return@addSyntax

            val player = sender as Player

            val mob = player.itemInMainHand.data?.get<Mob>(Mob.mobKey)!!

            MobSpawner.createSpawner(args.get(name), MobSpawner(player.instance!!, listOf(player.position.toBlockPosition()), mob))

            player.sendFormattedMessage(Component.text(mobSpawnerCreated), Component.text(args.get(name)))
        }

        addSyntax(spawner, limit, limitAmount, name) { sender, args ->

            val runtimeSpawner = MobSpawner.getSpawner(args.get(name))

            if (runtimeSpawner == null) {
                sender.sendFormattedMessage(Component.text(mobSpawnerNotFound))
                return@addSyntax
            }

            runtimeSpawner.limit = args.get(limitAmount)

            sender.sendFormattedMessage(Component.text(mobSpawnerLimit), Component.text(args.get(name)), Component.text(args.get(limitAmount).toString()))

        }

        addSyntax(spawner, tick, tickAmount, name) { sender, args ->

            val runtimeSpawner = MobSpawner.getSpawner(args.get(name))

            if (runtimeSpawner == null) {
                sender.sendFormattedMessage(Component.text(mobSpawnerNotFound))
                return@addSyntax
            }

            runtimeSpawner.ticksPerSpawn = args.get(tickAmount)

            sender.sendFormattedMessage(Component.text(mobSpawnerTickSpeed), Component.text(args.get(name)), Component.text(args.get(tickAmount).toString()))

        }
    }

    override fun onDynamicWrite(sender: CommandSender, text: String): Array<out String> {
        return files.map { it.nameWithoutExtension }.toTypedArray()
    }

    /**
     * Checks if the sender has a mob egg.
     *
     * @param sender The sender to check
     *
     * @return If the sender has the egg or not (false if they don't)
     */
    fun hasMobEgg(sender: CommandSender): Boolean {
        if (sender !is Player) return false

        if (sender.itemInMainHand.material == Material.AIR) {
            sender.sendFormattedMessage(Component.text(mustHaveItemInHand))
            return false
        }

        if (sender.itemInMainHand.data?.get<Mob>(Mob.mobKey) == null) {
            sender.sendFormattedMessage(Component.text(mobSpawnEggInHand))
            return false
        }

        return true
    }

}