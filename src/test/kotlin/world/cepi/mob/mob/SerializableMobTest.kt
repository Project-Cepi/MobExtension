package world.cepi.mob.mob

import net.minestom.server.utils.time.TimeUnit
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Disabled
import world.cepi.mob.goal.SerializableGoals
import world.cepi.mob.property.HealthProperty
import world.cepi.mob.targets.SerializableTargets
import java.time.Duration

class SerializableMobTest {
    @Disabled("odd NoClassFound err")
    fun `types should be properly serialized`() {
        val mob = Mob()
            .property(HealthProperty(20f))
            .goal(SerializableGoals.FollowTargetGoal(Duration.of(5, TimeUnit.SERVER_TICK)))
            .target(SerializableTargets.ClosestEntityTarget(10f))


        assertEquals(mob, Mob.fromJSON(mob.toJSON()))

    }
}