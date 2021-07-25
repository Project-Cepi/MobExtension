package world.cepi.mob.goal

import net.minestom.server.entity.EntityCreature
import net.minestom.server.entity.ai.GoalSelector
import net.minestom.server.utils.Position
import java.util.*

class RandomStrollGoal(entityCreature: EntityCreature, radius: Int) : GoalSelector(entityCreature) {
    private val closePositions: MutableList<Position?>
    private var lastStroll: Long = 0

    override fun shouldStart() = System.currentTimeMillis() - lastStroll >= DELAY

    override fun start() {
        closePositions.shuffle()
        for (position in closePositions) {
            val target = position!!.clone().add(entityCreature.position)
            val result = entityCreature.navigator.setPathTo(target)
            if (result) {
                break
            }
        }
    }

    override fun tick(time: Long) {}
    override fun shouldEnd() = true

    override fun end() {
        lastStroll = System.currentTimeMillis()
    }

    private fun getNearbyBlocks(radius: Int): MutableList<Position?> {
        val blocks: MutableList<Position?> = ArrayList()
        for (x in -radius..radius) {
            for (y in -radius..radius) {
                for (z in -radius..radius) {
                    val position = Position(x.toDouble(), y.toDouble(), z.toDouble())
                    blocks.add(position)
                }
            }
        }
        return blocks
    }

    companion object {
        private const val DELAY: Long = 2500
    }

    init {
        closePositions = getNearbyBlocks(radius)
    }
}