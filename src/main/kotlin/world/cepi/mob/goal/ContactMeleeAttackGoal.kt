package world.cepi.mob.goal

import net.minestom.server.entity.Entity
import net.minestom.server.entity.EntityCreature
import net.minestom.server.entity.Player
import net.minestom.server.entity.ai.GoalSelector
import net.minestom.server.utils.time.Cooldown
import net.minestom.server.utils.time.TimeUnit
import net.minestom.server.utils.time.UpdateOption

class ContactMeleeAttackGoal(
    entityCreature: EntityCreature,
    private val delayUpdateOption: UpdateOption
) :
    GoalSelector(entityCreature) {
    val cooldown = Cooldown(UpdateOption(5, TimeUnit.TICK))
    private var lastHit: Long = 0
    private var stop = false
    private var cachedTarget: Entity? = null
    override fun shouldStart(): Boolean {
        cachedTarget = findTarget()
        return cachedTarget != null
    }

    override fun start() {
        val targetPosition = cachedTarget!!.position
        entityCreature.navigator.setPathTo(targetPosition)
    }

    override fun tick(time: Long) {
        val target: Entity?
        if (cachedTarget != null) {
            target = cachedTarget
            cachedTarget = null
        } else {
            target = findTarget()
        }
        stop = target == null
                || (target as? Player)?.isCreative == true
        if (!stop) {

            // Attack the target entity
            if (entityCreature.boundingBox.intersect(target!!)) {
                if (!Cooldown.hasCooldown(time, lastHit, delayUpdateOption)) {
                    entityCreature.attack(target, true)
                    lastHit = time
                }
                return
            }

            // Move toward the target entity
            val navigator = entityCreature.navigator
            val pathPosition = navigator.pathPosition
            val targetPosition = target.position
            if (pathPosition == null || !pathPosition.isSimilar(targetPosition)) {
                if (cooldown.isReady(time)) {
                    cooldown.refreshLastUpdate(time)
                    navigator.setPathTo(targetPosition)
                }
            }
        }
    }

    override fun shouldEnd(): Boolean {
        return stop
    }

    override fun end() {
        // Stop following the target
        entityCreature.navigator.setPathTo(null)
    }
}
