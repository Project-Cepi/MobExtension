package world.cepi.mobextension

import net.minestom.server.extensions.Extension;

class MobExtension : Extension() {

    override fun initialize() {
        logger.info("[ExampleExtension] has been enabled!")
    }

    override fun terminate() {
        logger.info("[ExampleExtension] has been disabled!")
    }

}