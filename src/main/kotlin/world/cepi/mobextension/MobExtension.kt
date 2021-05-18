package world.cepi.mobextension

import net.minestom.server.MinecraftServer
import net.minestom.server.entity.Player
import net.minestom.server.extensions.Extension
import world.cepi.kstom.addEventCallback
import world.cepi.kstom.command.register
import world.cepi.kstom.command.unregister
import world.cepi.mobextension.commands.MobCommand
import world.cepi.mobextension.mob.events.MobInfoHook
import world.cepi.mobextension.mob.events.MobSpawnHook
import java.io.File

class MobExtension : Extension() {

    private val playerInitialization: (Player) -> Unit = {
        it.addEventCallback(MobSpawnHook::hook)
        it.addEventCallback(MobInfoHook::hook)
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