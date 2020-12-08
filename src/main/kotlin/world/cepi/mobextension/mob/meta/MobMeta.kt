package world.cepi.mobextension.mob.meta

import net.minestom.server.entity.Entity

/** Class used for handling MobMeta. */
interface MobMeta {

    fun apply(entity: Entity)
}