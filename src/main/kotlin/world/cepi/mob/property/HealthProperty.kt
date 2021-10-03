package world.cepi.mob.property

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import net.minestom.server.attribute.Attribute
import net.minestom.server.entity.EntityCreature

@Serializable
@SerialName("health")
data class HealthProperty(@SerialName("value") val health: Float) : MobProperty() {
    override fun apply(entityCreature: EntityCreature) {
        entityCreature.getAttribute(Attribute.MAX_HEALTH).baseValue = health
        entityCreature.health = health

    }

}