package world.cepi.mob.goal

import net.minestom.server.coordinate.Pos
import net.minestom.server.entity.Entity
import net.minestom.server.entity.EntityCreature
import net.minestom.server.entity.ai.GoalSelector
import java.time.Duration

class FollowTargetGoal(entityCreature: EntityCreature, private val pathDuration: Duration) :
    GoalSelector(entityCreature) {
    private var lastUpdateTime: Long = 0
    private var forceEnd = false
    private var lastTargetPos: Pos? = null
    private var target: Entity? = null
    override fun shouldStart(): Boolean {
        var target = entityCreature.target
        if (target == null) target = findTarget()
        if (target == null) return false
        val result = target.position.distance(entityCreature.position) >= 2
        if (result) {
            this.target = target
        }
        return result
    }

    override fun start() {
        lastUpdateTime = 0
        forceEnd = false
        lastTargetPos = null
        if (target == null) {
            // No defined target
            forceEnd = true
            return
        }
        entityCreature.target = target
        val navigator = entityCreature.navigator
        lastTargetPos = target!!.position
        if (lastTargetPos!!.distance(entityCreature.position) < 2) {
            // Target is too far
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
    }

    override fun tick(time: Long) {
        if (forceEnd ||
            pathDuration.isZero || pathDuration.toMillis() + lastUpdateTime > time
        ) {
            return
        }
        val targetPos = if (entityCreature.target != null) entityCreature.target!!
            .position else null
        if (targetPos != null && !targetPos.samePoint(lastTargetPos!!)) {
            lastUpdateTime = time
            lastTargetPos = targetPos
            entityCreature.navigator.setPathTo(targetPos)
        }
    }

    override fun shouldEnd(): Boolean {
        val target = entityCreature.target
        return forceEnd || target == null ||
                target.isRemoved || target.position.distance(entityCreature.position) < 2
    }

    override fun end() {
        entityCreature.navigator.setPathTo(null)
    }
}
