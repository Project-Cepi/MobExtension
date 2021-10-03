package world.cepi.mob.property

import kotlinx.serialization.Serializable
import net.minestom.server.entity.EntityCreature

@Serializable
sealed class MobProperty {

    abstract fun apply(creature: EntityCreature)

}