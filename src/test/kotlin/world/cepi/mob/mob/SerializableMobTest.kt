package world.cepi.mob.mob

import net.minestom.server.utils.time.TimeUnit
import net.minestom.server.utils.time.UpdateOption
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import world.cepi.mob.goal.SerializableGoals
import world.cepi.mob.meta.HealthMeta
import world.cepi.mob.targets.SerializableTargets

class SerializableMobTest {
    @Test
    @Disabled("UpdateOption needs to call equals properly. ")
    fun `types should be properly serialized`() {
        val mob = Mob()
            .addMeta(HealthMeta(20f))
            .addGoal(SerializableGoals.FollowTargetGoal(UpdateOption(5, TimeUnit.TICK)))
            .addTarget(SerializableTargets.SimplifiedClosestEntityTarget(10f))


        assertEquals(mob.asSerializable(), SerializableMob.fromJSON(mob.asSerializable().toJSON()))

    }
}