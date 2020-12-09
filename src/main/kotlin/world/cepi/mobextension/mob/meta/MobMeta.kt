package world.cepi.mobextension.mob.meta

import net.minestom.server.entity.Entity

/**
 * Class used for handling MobMeta.
 *
 * All classes that extend this must be serializable,
 * data classes, be in the json handler, and its SerialName for properties
 * must be "value" for peak performance.
 */
interface MobMeta {

    fun apply(entity: Entity)
}