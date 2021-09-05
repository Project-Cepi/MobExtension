package world.cepi.mob.mob

import net.minestom.server.entity.EntityType
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.fail

class TypeTest {

    @Test
    fun `stop duplicate materials from appearing`() {

        val distinctValues = EntityEggData.values().distinctBy { it.type }

        if (distinctValues.size != EntityEggData.values().size) {
            fail("Duplicate Entries: \n ${EntityEggData.values().filter { !distinctValues.contains(it) }
                .joinToString("\n")
            }")
        }
    }

    @Test
    fun `ensure all mob types are present`() {
        EntityType.values().map {
            EntityEggData.findByType(it) to it
        }.filter { it.first == null }
            .map { it.second }
            .let {
                if (it.isNotEmpty()) {
                    fail("Missing types:\n ${it.joinToString("\n")}")
                }
            }
    }

}