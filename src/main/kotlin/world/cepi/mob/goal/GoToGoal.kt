package world.cepi.mob.goal

import net.minestom.server.entity.EntityCreature
import net.minestom.server.entity.ai.GoalSelector
import net.minestom.server.utils.Vector

class GoToGoal(
    entityCreature: EntityCreature,
    /** The entity's "home" */
    origin: Vector,
    /** Min distance before it stops going to the goal. Reccomended at least 1 **/
    val minDistance: Double,
) : GoalSelector(entityCreature) {

    val origin = origin.toPosition()

    override fun shouldStart(): Boolean =
        entityCreature.position.getDistance(origin) > minDistance

    override fun shouldEnd(): Boolean =
        entityCreature.position.getDistance(origin) <= minDistance

    override fun start() {
        entityCreature.navigator.setPathTo(origin)
    }

    override fun end() {
        entityCreature.navigator.setPathTo(null)
    }

    override fun tick(time: Long) {

    }

}