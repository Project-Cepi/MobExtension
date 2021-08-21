package world.cepi.mob.goal

import net.minestom.server.coordinate.Vec
import net.minestom.server.entity.EntityCreature
import net.minestom.server.entity.ai.GoalSelector

class GoToGoal(
    entityCreature: EntityCreature,
    /** The entity's "home" */
    origin: Vec,
    /** Min distance before it stops going to the goal. Reccomended at least 1 **/
    val minDistance: Double,
) : GoalSelector(entityCreature) {

    val origin = origin.asPosition()

    override fun shouldStart(): Boolean =
        entityCreature.position.distance(origin) > minDistance

    override fun shouldEnd(): Boolean =
        entityCreature.position.distance(origin) <= minDistance

    override fun start() {
        entityCreature.navigator.setPathTo(origin)
    }

    override fun end() {
        entityCreature.navigator.setPathTo(null)
    }

    override fun tick(time: Long) {

    }

}