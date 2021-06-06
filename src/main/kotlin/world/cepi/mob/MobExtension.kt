package world.cepi.mob

import net.minestom.server.MinecraftServer
import net.minestom.server.entity.Player
import net.minestom.server.extensions.Extension
import world.cepi.kstom.addEventCallback
import world.cepi.kstom.command.register
import world.cepi.kstom.command.unregister
import world.cepi.mob.commands.MobCommand
import world.cepi.mob.events.MobInfoHook
import world.cepi.mob.events.MobSpawnHook
import java.io.File

class MobExtension : Extension() {

    private val playerInitialization: (Player) -> Unit = {
        it.addEventCallback(MobSpawnHook::hook)
        it.addEventCallback(MobInfoHook::hookDig)
        it.addEventCallback(MobInfoHook::hookInteract)
    }

    override fun initialize() {

        MobCommand.register()

        MinecraftServer.getConnectionManager().addPlayerInitialization(playerInitialization)

        logger.info("[MobExtension] has been enabled!")
    }

    override fun terminate() {

        MobCommand.unregister()

        MinecraftServer.getConnectionManager().removePlayerInitialization(playerInitialization)

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