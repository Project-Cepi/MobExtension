package world.cepi.mob.goal

import net.minestom.server.entity.EntityCreature
import net.minestom.server.entity.ai.GoalSelector
import net.minestom.server.utils.MathUtils
import net.minestom.server.utils.Position
import net.minestom.server.utils.time.UpdateOption

class FollowTargetGoal(
    entityCreature: EntityCreature,
    private val pathUpdateOption: UpdateOption
) :
    GoalSelector(entityCreature) {

    private var lastUpdateTime: Long = 0
    private var forceEnd = false
    private var lastTargetPos: Position? = null

    override fun shouldStart(): Boolean {
        return entityCreature.target != null &&
                getDistance(entityCreature.target!!.position, entityCreature.position) >= 2
    }

    override fun start() {
        lastUpdateTime = 0
        forceEnd = false
        lastTargetPos = null
        val target = entityCreature.target
        if (target != null) {
            val navigator = entityCreature.navigator
            lastTargetPos = target.position.clone()
            if (getDistance(lastTargetPos!!, entityCreature.position) < 2) {
                forceEnd = true
                navigator.setPathTo(null)
                return
            }
            if (navigator.pathPosition == null ||
                !navigator.pathPosition!!.isSimilar(lastTargetPos!!)
            ) {
                navigator.setPathTo(lastTargetPos)
            } else {
                forceEnd = true
            }
        } else {
            forceEnd = true
        }
    }

    override fun tick(time: Long) {
        if (forceEnd || pathUpdateOption.value == 0L || pathUpdateOption.timeUnit.toMilliseconds(pathUpdateOption.value) + lastUpdateTime > time) {
            return
        }
        val targetPos = if (entityCreature.target != null) entityCreature.target!!
            .position else null
        if (targetPos != null && targetPos != lastTargetPos) {
            lastUpdateTime = time
            lastTargetPos!!.copy(lastTargetPos!!)
            entityCreature.navigator.setPathTo(targetPos)
        }
    }

    override fun shouldEnd(): Boolean {
        return forceEnd || entityCreature.target == null || getDistance(
            entityCreature.target!!.position,
            entityCreature.position
        ) < 2
    }

    override fun end() {
        entityCreature.navigator.setPathTo(null)
    }

    private fun getDistance(a: Position, b: Position): Double {
        return MathUtils.square(a.x - b.x) +
                MathUtils.square(a.z - b.z)
    }
}