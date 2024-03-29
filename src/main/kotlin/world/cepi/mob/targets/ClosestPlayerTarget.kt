package world.cepi.mob.targets

import net.minestom.server.entity.Entity
import net.minestom.server.entity.EntityCreature
import net.minestom.server.entity.Player
import net.minestom.server.entity.ai.TargetSelector
import world.cepi.mob.util.MobUtils

/**
 * Target the closest targetable entity (based on the class array)
 */
class ClosestPlayerTarget(entityCreature: EntityCreature, private val range: Float) : TargetSelector(entityCreature) {
    override fun findTarget(): Entity? {
        val instance = entityCreature.instance
        val currentChunk = instance!!.getChunkAt(entityCreature.position) ?: return null
        val chunks = MobUtils.getNeighbours(instance, currentChunk.chunkX, currentChunk.chunkZ)
        var entity: Entity? = null
        var distance = Double.MAX_VALUE
        for (chunk in chunks) {
            val entities = instance.getChunkEntities(chunk)
            for (ent in entities) {

                if (ent !is Player) {
                    continue
                }

                if (!MobUtils.isValidTarget(ent, entityCreature)) {
                    continue
                }

                // Check distance
                val d = entityCreature.getDistance(ent)
                if ((entity == null || d < distance) && d < range) {
                    entity = ent
                    distance = d
                    continue
                }
            }
        }
        return entity
    }
}