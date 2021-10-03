package world.cepi.mob.property

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import net.minestom.server.entity.EntityCreature

@Serializable
@SerialName("noGravity")
class NoGravityProperty : MobProperty() {
    override fun apply(entityCreature: EntityCreature) {
        entityCreature.setNoGravity(true)
    }

}