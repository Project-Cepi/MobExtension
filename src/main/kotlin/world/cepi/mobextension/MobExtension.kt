package world.cepi.mobextension

import net.minestom.server.MinecraftServer
import net.minestom.server.entity.Player
import net.minestom.server.event.player.PlayerUseItemOnBlockEvent
import net.minestom.server.extensions.Extension
import world.cepi.kstom.addEventCallback
import world.cepi.mobextension.commands.MobCommand
import java.io.File

class MobExtension : Extension() {

    val playerInitialization: (Player) -> Unit = {
        it.addEventCallback(PlayerUseItemOnBlockEvent::class, ::mobSpawnEvent)
    }

    override fun initialize() {

        MinecraftServer.getCommandManager().register(MobCommand)

        MinecraftServer.getConnectionManager().addPlayerInitialization(playerInitialization)

        logger.info("[MobExtension] has been enabled!")
    }

    override fun terminate() {

        MinecraftServer.getCommandManager().unregister(MobCommand)

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