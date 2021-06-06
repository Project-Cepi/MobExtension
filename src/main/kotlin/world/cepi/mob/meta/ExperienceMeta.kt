package world.cepi.mob.meta

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import net.minestom.server.entity.Entity
import net.minestom.server.event.entity.EntityDeathEvent
import world.cepi.kstom.addEventCallback

@Serializable
@SerialName("experience")
data class ExperienceMeta(@SerialName("value") val experience: Int) : MobMeta() {
    override fun apply(entity: Entity) {
        entity.addEventCallback<EntityDeathEvent> {
            // TODO give experience to player
        }
    }
}