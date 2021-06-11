package world.cepi.mob.meta

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import net.minestom.server.entity.Entity
import net.minestom.server.event.EventFilter
import net.minestom.server.event.EventNode
import net.minestom.server.event.entity.EntityDeathEvent
import world.cepi.kstom.event.listenOnly

@Serializable
@SerialName("experience")
data class ExperienceMeta(@SerialName("value") val experience: Int) : MobMeta() {
    override fun apply(entity: Entity) {
        entities.add(entity)
    }

    companion object {
        private val entities: MutableList<Entity> = mutableListOf()
        private val node = EventNode.type("mob-entity-death-experience", EventFilter.ENTITY) { _, obj ->
            entities.contains(obj)
        }

        init {
            node.listenOnly<EntityDeathEvent> {
                // TODO add experience to killers
            }
        }
    }
}