package world.cepi.mob

import net.minestom.server.event.EventFilter
import net.minestom.server.event.EventNode
import net.minestom.server.extensions.Extension
import world.cepi.kstom.Manager
import world.cepi.kstom.event.listenOnly
import world.cepi.mob.commands.MobCommand
import world.cepi.mob.events.MobSpawnHook
import world.cepi.mob.events.MobUIHook
import world.cepi.mob.mob.Mob
import world.cepi.mob.spawner.MobSpawner
import java.io.File

class MobExtension : Extension() {
    override fun initialize() {

        val playerNode = EventNode.type("general-mob-hooks", EventFilter.PLAYER)

        playerNode.listenOnly(MobSpawnHook::hook)
        playerNode.listenOnly(MobSpawnHook::hookInteract)
        playerNode.listenOnly(MobUIHook::hookDig)
        playerNode.listenOnly(MobUIHook::hookAnimation)

        eventNode.addChild(MobSpawner.allNode)

        eventNode.addChild(playerNode)

        MobCommand.register()

        logger.info("[MobExtension] has been enabled!")
    }

    override fun postInitialize() {
        val itemExtension = Manager.extension.getExtension("ItemExtension")

        if (itemExtension != null)
            itemExtension.eventNode.addChild(Mob.mobEventNode)
        else
            eventNode.addChild(Mob.mobEventNode)
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