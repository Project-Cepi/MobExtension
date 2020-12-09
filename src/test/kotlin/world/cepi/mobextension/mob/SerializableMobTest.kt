package world.cepi.mobextension.mob

import net.minestom.server.entity.EntityType
import org.junit.jupiter.api.Test
import world.cepi.mobextension.goal.serializable.SerializableGoals
import world.cepi.mobextension.mob.meta.HealthMeta
import world.cepi.mobextension.mob.meta.NameMeta

class SerializableMobTest {

    private fun testMob(mob: Mob): Boolean {
        return SerializableMob.fromJSON(mob.asSerializable().toJSON()) == mob.asSerializable()
    }

    @Test
    fun `types should be properly serialized`() {
        val mobOnlyType = Mob(Mob.Properties().setType(EntityType.SLIME))

        assert(testMob(mobOnlyType))

        val mobMeta = Mob(Mob.Properties()
                .addMeta(HealthMeta(20f))
                .addMeta(NameMeta("Llamao"))
        )

        assert(testMob(mobMeta))

        val mobGoals = Mob(Mob.Properties()
                .addMeta(HealthMeta(20f))
                .addGoal(SerializableGoals.RandomStrollGoal(5))
        )

        assert(testMob(mobGoals))

    }
}