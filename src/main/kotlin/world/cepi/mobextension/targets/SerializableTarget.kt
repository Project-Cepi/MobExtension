package world.cepi.mobextension.targets

import net.minestom.server.entity.EntityCreature
import net.minestom.server.entity.ai.TargetSelector

interface SerializableTarget {

    fun toTarget(creature: EntityCreature): TargetSelector

}