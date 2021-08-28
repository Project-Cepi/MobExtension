package world.cepi.mob.mob

import net.minestom.server.utils.time.TimeUnit
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Disabled
import world.cepi.mob.goal.SerializableGoals
import world.cepi.mob.generator.HealthMeta
import world.cepi.mob.targets.SerializableTargets
import java.time.Duration

class SerializableMobTest {
    @Disabled("odd NoClassFound err")
    fun `types should be properly serialized`() {
        val mob = Mob()
            .addMeta(HealthMeta(20f))
            .addGoal(SerializableGoals.FollowTargetGoal(Duration.of(5, TimeUnit.SERVER_TICK)))
            .addTarget(SerializableTargets.ClosestEntityTarget(10f))


        assertEquals(mob, Mob.fromJSON(mob.toJSON()))

    }
}