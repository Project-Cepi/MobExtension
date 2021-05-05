package world.cepi.mobextension.goal

import net.minestom.server.entity.EntityCreature
import net.minestom.server.entity.ai.GoalSelector
import net.minestom.server.utils.Vector

class GoToGoal(
    entityCreature: EntityCreature,
    /** The entity's "home" */
    val origin: Vector,
    /** Max distance it can go away from its origin before coming back. */
    val maxDistance: Double,
    /** Min distance before it stops going to the goal. Reccomended at least 1 **/
    val minDistance: Double,
) : GoalSelector(entityCreature) {

    override fun shouldStart(): Boolean =
        entityCreature.position.getDistance(origin.toPosition()) >= maxDistance

    override fun shouldEnd(): Boolean =
        entityCreature.position.getDistance(origin.toPosition()) < minDistance

    override fun start() {
        entityCreature.navigator.setPathTo(origin.toPosition())
    }

    override fun end() {

    }

    override fun tick(time: Long) {

    }

}