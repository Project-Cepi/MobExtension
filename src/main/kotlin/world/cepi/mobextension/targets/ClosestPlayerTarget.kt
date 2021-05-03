package world.cepi.mobextension.targets

import net.minestom.server.entity.Entity
import net.minestom.server.entity.EntityCreature
import net.minestom.server.entity.GameMode
import net.minestom.server.entity.Player
import net.minestom.server.entity.ai.TargetSelector

/**
 * Target the closest targetable entity (based on the class array)
 */
class ClosestPlayerTarget(entityCreature: EntityCreature, private val range: Float) : TargetSelector(entityCreature) {
    override fun findTarget(): Entity? {
        val instance = getEntityCreature().instance
        val currentChunk = instance!!.getChunkAt(entityCreature.position) ?: return null
        val chunks = TargetUtils.getNeighbours(instance, currentChunk.chunkX, currentChunk.chunkZ)
        var entity: Entity? = null
        var distance = Double.MAX_VALUE
        for (chunk in chunks) {
            val entities = instance.getChunkEntities(chunk)
            for (ent in entities) {

                // Only target players
                if (ent !is Player) {
                    continue
                }

                if (ent.isCreative || ent.gameMode == GameMode.SPECTATOR) {
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