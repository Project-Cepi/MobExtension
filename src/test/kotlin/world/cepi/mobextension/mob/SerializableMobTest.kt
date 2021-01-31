package world.cepi.mobextension.mob

import net.minestom.server.entity.EntityType
import net.minestom.server.utils.time.TimeUnit
import org.junit.jupiter.api.Test
import world.cepi.mobextension.Mob
import world.cepi.mobextension.SerializableMob
import world.cepi.mobextension.asSerializable
import world.cepi.mobextension.goal.SerializableGoals
import world.cepi.mobextension.meta.HealthMeta
import world.cepi.mobextension.meta.NameMeta
import world.cepi.mobextension.targets.SerializableTargets

class SerializableMobTest {

    private fun testMob(mob: Mob): Boolean {
        println(SerializableMob.fromJSON(mob.asSerializable().toJSON()))
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

        val mobTargets = Mob(Mob.Properties()
                .addMeta(HealthMeta(20f))
                .addGoal(SerializableGoals.FollowTargetGoal(5, TimeUnit.TICK))
                .addTarget(SerializableTargets.SimplifiedClosestEntityTarget(10f))
        )

        println(mobTargets.asSerializable().toJSON())

        assert(testMob(mobTargets))

    }
}