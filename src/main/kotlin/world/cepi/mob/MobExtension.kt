package world.cepi.mob

import net.minestom.server.event.EventFilter
import net.minestom.server.event.EventNode
import net.minestom.server.extensions.Extension
import world.cepi.kstom.command.register
import world.cepi.kstom.command.unregister
import world.cepi.kstom.event.listenOnly
import world.cepi.mob.commands.MobCommand
import world.cepi.mob.events.MobInfoHook
import world.cepi.mob.events.MobSpawnHook
import world.cepi.mob.spawner.MobSpawner
import java.io.File

class MobExtension : Extension() {
    override fun initialize() {

        val playerNode = EventNode.type("general-mob-hooks", EventFilter.ALL)

        playerNode.listenOnly(MobSpawnHook::hook)
        playerNode.listenOnly(MobInfoHook::hookDig)
        playerNode.listenOnly(MobInfoHook::hookInteract)

        eventNode.addChild(MobSpawner.allNode)

        eventNode.addChild(playerNode)

        MobCommand.register()

        logger.info("[MobExtension] has been enabled!")
    }

    override fun terminate() {

        MobCommand.unregister()

        logger.info("[MobExtension] has been disabled!")
    }

    companion object {
     val dataDir: File
        get() {
            val dir = File("./extensions/MobExtension")
            if (!dir.exists()) dir.mkdirs()
            return dir
        }
    }
}