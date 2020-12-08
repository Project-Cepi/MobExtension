package world.cepi.mobextension.mob

import net.minestom.server.entity.EntityType
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class SerializableMobTest {

    @Test
    fun `types should be properly serialized`() {
        val mob = Mob(Mob.Properties().setType(EntityType.SLIME))
        assertEquals(SerializableMob.fromJSON(mob.asSerializable().toJSON()), mob.asSerializable())
        println(mob.asSerializable().toJSON())
    }
}