package world.cepi.mob.goal

import net.minestom.server.entity.EntityCreature
import net.minestom.server.entity.ai.GoalSelector
import java.util.*

class DoNothingGoal(entityCreature: EntityCreature?, private val time: Long, chance: Float) : GoalSelector(
    entityCreature!!
) {
    private val chance: Float = chance.coerceIn(0f, 1f)
    private var startTime: Long = 0
    override fun end() {
        startTime = 0
    }

    override fun shouldEnd() = System.currentTimeMillis() - startTime >= time

    override fun shouldStart() = RANDOM.nextFloat() <= chance

    override fun start() {
        startTime = System.currentTimeMillis()
    }

    override fun tick(time: Long) {}

    companion object {
        private val RANDOM = Random()
    }

}