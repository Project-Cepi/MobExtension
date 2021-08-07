package world.cepi.mob.meta

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import net.minestom.server.entity.Entity
import net.minestom.server.entity.LivingEntity
import net.minestom.server.entity.Player
import net.minestom.server.entity.damage.EntityDamage
import net.minestom.server.event.EventFilter
import net.minestom.server.event.EventNode
import net.minestom.server.event.entity.EntityDeathEvent
import net.minestom.server.tag.Tag
import world.cepi.kstom.event.listenOnly
import world.cepi.level.ExperienceManager

@Serializable
@SerialName("experience")
data class ExperienceMeta(@SerialName("value") val experience: Int) : MobMeta() {
    override fun apply(entity: Entity) {
        entities.add(entity)
        entity.setTag(Tag.Integer("experience"), experience)
    }

    companion object {
        private val entities: MutableList<Entity> = mutableListOf()
        private val node = EventNode.type("mob-entity-death-experience", EventFilter.ENTITY) { _, obj ->
            entities.contains(obj)
        }

        init {
            node.listenOnly<EntityDeathEvent> {
                ExperienceManager.addExperience(
                    (((entity as? LivingEntity)?.lastDamageSource as? EntityDamage)?.source as? Player) ?: return@listenOnly,
                    entity.getTag(Tag.Integer("experience")) ?: return@listenOnly
                )
                entities.remove(entity)
            }
        }
    }
}