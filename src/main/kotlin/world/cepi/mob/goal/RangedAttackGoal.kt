package world.cepi.mob.goal

import net.minestom.server.entity.Entity
import net.minestom.server.entity.EntityCreature
import net.minestom.server.entity.EntityProjectile
import net.minestom.server.entity.ai.GoalSelector
import net.minestom.server.utils.time.Cooldown
import net.minestom.server.utils.time.TimeUnit
import java.time.Duration

class RangedAttackGoal(
    entityCreature: EntityCreature,
    private val delay: Duration,
    attackRange: Int,
    desirableRange: Int,
    val comeClose: Boolean,
    val power: Double,
    val spread: Double,
    val projectileGenerator: (Entity) -> EntityProjectile
) : GoalSelector(entityCreature) {
    val cooldown = Cooldown(Duration.of(5, TimeUnit.SERVER_TICK))
    private var lastShot: Long = 0
    private val attackRangeSquared: Int = attackRange * attackRange
    private val desirableRangeSquared: Int = desirableRange * desirableRange
    private var stop = false
    private var cachedTarget: Entity? = null

    override fun shouldStart(): Boolean {
        cachedTarget = findTarget()
        return cachedTarget != null
    }

    override fun start() {
        entityCreature.navigator.setPathTo(cachedTarget!!.position)
    }

    override fun tick(time: Long) {
        val target: Entity?
        if (cachedTarget != null) {
            target = cachedTarget
            cachedTarget = null
        } else {
            target = findTarget()
        }
        if (target == null) {
            stop = true
            return
        }
        val distanceSquared = entityCreature.getDistanceSquared(target)
        var comeClose = false
        if (distanceSquared <= attackRangeSquared) {
            if (!Cooldown.hasCooldown(time, lastShot, delay)) {
                if (entityCreature.hasLineOfSight(target)) {
                    val to = target.position.clone().add(0.0, target.eyeHeight, 0.0)
                    val projectile = projectileGenerator(entityCreature)
                    projectile.setInstance(entityCreature.instance!!, entityCreature.position)
                    projectile.shoot(to, power, spread)
                    lastShot = time
                } else {
                    comeClose = this.comeClose
                }
            }
        }
        val navigator = entityCreature.navigator
        val pathPosition = navigator.pathPosition
        if (!comeClose && distanceSquared <= desirableRangeSquared) {
            if (pathPosition != null) {
                navigator.setPathTo(null)
            }
            return
        }
        val targetPosition = target.position
        if (pathPosition == null || !pathPosition.isSimilar(targetPosition)) {
            if (cooldown.isReady(time)) {
                cooldown.refreshLastUpdate(time)
                navigator.setPathTo(targetPosition)
            }
        }
    }

    override fun shouldEnd() = stop

    override fun end() {
        // Stop following the target
        entityCreature.navigator.setPathTo(null)
    }

    init {
        require(desirableRange > attackRange) { "Desirable range can not exceed attack range!" }
    }
}