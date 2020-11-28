package world.cepi.mobextension

import net.minestom.server.extensions.Extension;
import java.io.File

class MobExtension : Extension() {


    override fun initialize() {
        logger.info("[MobExtension] has been enabled!")
    }

    override fun terminate() {
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