package world.cepi.mobextension.mob

import net.minestom.server.entity.EntityType
import world.cepi.mobextension.genRandomID

class Mob(
        val id: String = genRandomID(),
        val type: EntityType = EntityType.LLAMA
) {
}