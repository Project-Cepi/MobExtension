package world.cepi.mobextension.targets

import net.minestom.server.entity.EntityCreature
import net.minestom.server.entity.ai.TargetSelector

/** A target serializable by kotlinx.serializable */
interface SerializableTarget {

    /**
     * Turns a [SerializableTarget] into a [TargetSelector]
     *
     * @param creature The creature to use to convert the targe tto
     * @return THe converted [SerializableTarget] as a [TargetSelector]
     */
    fun toTarget(creature: EntityCreature): TargetSelector

}