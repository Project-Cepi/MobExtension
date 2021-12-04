package world.cepi.mob.events

import net.minestom.server.entity.Entity
import net.minestom.server.entity.Player
import net.minestom.server.event.trait.EntityEvent

/**
 * Triggers when an entity is interacted with
 */
class EntityInteractEvent(private val _entity: Entity, val source: Player) : EntityEvent {

    override fun getEntity() = _entity

}