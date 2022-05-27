package world.cepi.mob

import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlinx.serialization.serializer
import net.minestom.server.event.EventFilter
import net.minestom.server.event.EventNode
import net.minestom.server.extensions.Extension
import world.cepi.actions.ActionSerializer
import world.cepi.kstom.event.listenOnly
import world.cepi.kstom.util.log
import world.cepi.kstom.util.node
import world.cepi.mob.commands.MobCommand
import world.cepi.mob.events.MobInteractHook
import world.cepi.mob.events.MobSpawnHook
import world.cepi.mob.events.MobUIHook
import world.cepi.mob.mob.Mob
import world.cepi.mob.spawner.MobSpawner
import java.io.File
import kotlin.io.path.*

class MobExtension : Extension() {

    val format = Json { serializersModule = ActionSerializer.module }
    private val spawnersFile by lazy { dataDirectory().resolve("mob-spawners.json") }

    override fun initialize(): LoadStatus {

        if (spawnersFile.exists()) {
            MobSpawner.mutableSpawners = format.decodeFromString(spawnersFile.readText())
        } else {
            dataDirectory().createDirectories()
            spawnersFile.createFile()
        }

        val playerNode = EventNode.type("general-mob-hooks", EventFilter.PLAYER)

        playerNode.listenOnly(MobSpawnHook::hook)
        playerNode.listenOnly(MobSpawnHook::hookInteract)
        playerNode.listenOnly(MobUIHook::hookDig)
        playerNode.listenOnly(MobUIHook::hookAnimation)
        playerNode.listenOnly(MobInteractHook::hook)

        node.addChild(playerNode)

        node.addChild(MobSpawner.allNode)

        MobCommand.register()

        log.info("[MobExtension] has been enabled!")

        return LoadStatus.SUCCESS
    }

    override fun terminate() {

        spawnersFile.writeText(format.encodeToString(MobSpawner.mutableSpawners))

        MobCommand.unregister()

        log.info("[MobExtension] has been disabled!")
    }

    companion object {
        val dataDir: File get() {
            val dir = File("./extensions/MobExtension")
            if (!dir.exists()) dir.mkdirs()
            return dir
        }
    }
}