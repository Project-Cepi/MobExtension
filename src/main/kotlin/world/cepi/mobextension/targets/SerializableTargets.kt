package world.cepi.mobextension.targets

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import net.minestom.server.entity.EntityCreature
import net.minestom.server.entity.ai.TargetSelector
import net.minestom.server.entity.ai.target.ClosestEntityTarget
import net.minestom.server.entity.ai.target.LastEntityDamagerTarget

object SerializableTargets {

    @SerialName("closest_entity")
    @Serializable
    data class ClosestEntityTarget(val range: Float): SerializableTarget {
        override fun toTarget(creature: EntityCreature): TargetSelector = ClosestEntityTarget(creature, range, EntityCreature::class.java)
    }

    @SerialName("last_damager")
    @Serializable
    data class LastEntityDamagerTarget(val range: Float): SerializableTarget {
        override fun toTarget(creature: EntityCreature): TargetSelector = LastEntityDamagerTarget(creature, range)
    }

}