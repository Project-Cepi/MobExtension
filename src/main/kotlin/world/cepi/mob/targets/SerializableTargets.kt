package world.cepi.mob.targets

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import net.minestom.server.entity.EntityCreature
import net.minestom.server.entity.ai.TargetSelector
import world.cepi.kstom.command.arguments.generation.annotations.DefaultNumber
import world.cepi.kstom.command.arguments.generation.annotations.MaxAmount
import world.cepi.kstom.command.arguments.generation.annotations.MinAmount
import world.cepi.kstom.serializer.SerializableEntityFinder

object SerializableTargets {

    @SerialName("closest_entity")
    @Serializable
    data class ClosestEntityTarget(
        @param:MinAmount(1.0)
        @param:DefaultNumber(10.0)
        @param:MaxAmount(100.0)
        val range: Float
    ): SerializableTarget() {
        override fun toTarget(creature: EntityCreature): TargetSelector =
            ClosestEntityTarget(creature, range)
    }

    @SerialName("last_damager")
    @Serializable
    data class LastEntityDamagerTarget(
        @param:MinAmount(1.0)
        @param:DefaultNumber(10.0)
        @param:MaxAmount(100.0)
        val range: Float
    ): SerializableTarget() {
        override fun toTarget(creature: EntityCreature): TargetSelector =
            LastEntityDamagerTarget(creature, range)
    }

    @SerialName("closest_player")
    @Serializable
    data class ClosestPlayerTarget(
        @param:MinAmount(1.0)
        @param:DefaultNumber(10.0)
        @param:MaxAmount(100.0)
        val range: Float
    ): SerializableTarget() {
        override fun toTarget(creature: EntityCreature): TargetSelector =
            ClosestPlayerTarget(creature, range)
    }

    @SerialName("target_selector")
    @Serializable
    data class SelectorTarget(val entitySelector: SerializableEntityFinder): SerializableTarget() {
        override fun toTarget(creature: EntityCreature): TargetSelector =
            SelectorTarget(creature, entitySelector.get())
    }

}