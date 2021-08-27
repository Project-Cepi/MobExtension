package world.cepi.mob.meta

import kotlinx.serialization.Serializable
import net.minestom.server.entity.Entity

/**
 * Class used for handling MobMeta.
 *
 * All classes that extend this must be serializable and data classes.
 */
@Serializable
abstract class MobMeta {

    abstract fun apply(entity: Entity)

}