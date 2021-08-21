package world.cepi.mob.goal

import net.minestom.server.coordinate.Pos
import net.minestom.server.entity.EntityCreature
import net.minestom.server.entity.ai.GoalSelector
import java.time.Duration

class FollowTargetGoal(
    entityCreature: EntityCreature,
    private val pathUpdateOption: Duration
) :
    GoalSelector(entityCreature) {

    private var lastUpdateTime: Long = 0
    private var forceEnd = false
    private var lastTargetPos: Pos? = null

    override fun shouldStart(): Boolean {
        return entityCreature.target != null &&
                entityCreature.target!!.position.distance(entityCreature.position) >= 2
    }

    override fun start() {
        lastUpdateTime = 0
        forceEnd = false
        lastTargetPos = null
        val target = entityCreature.target
        if (target != null) {
            val navigator = entityCreature.navigator
            lastTargetPos = target.position
            if (lastTargetPos!!.distance(entityCreature.position) < 2) {
                forceEnd = true
                navigator.setPathTo(null)
                return
            }
            if (navigator.pathPosition == null ||
                !navigator.pathPosition!!.samePoint(lastTargetPos!!)
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
        if (forceEnd || pathUpdateOption.toMillis() == 0L || pathUpdateOption.toMillis() + lastUpdateTime > time) {
            return
        }
        val targetPos = if (entityCreature.target != null) entityCreature.target!!
            .position else null
        if (targetPos != null && targetPos != lastTargetPos) {
            lastUpdateTime = time
            entityCreature.navigator.setPathTo(targetPos)
        }
    }

    override fun shouldEnd(): Boolean {
        return forceEnd || entityCreature.target == null ||
            entityCreature.target!!.position.distance(
                entityCreature.position
            ) < 2
    }

    override fun end() {
        entityCreature.navigator.setPathTo(null)
    }
}