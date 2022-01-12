package world.cepi.mob.targets

import net.minestom.server.entity.Entity
import net.minestom.server.entity.EntityCreature
import net.minestom.server.entity.ai.TargetSelector
import world.cepi.mob.util.MobUtils

class ClosestEntityTarget(entityCreature: EntityCreature, private val range: Float) : TargetSelector(entityCreature) {
    override fun findTarget(): Entity? {
        val instance = getEntityCreature().instance ?: return null
        val currentChunk = instance.getChunkAt(entityCreature.position) ?: return null
        val chunks = MobUtils.getNeighbours(instance, currentChunk.chunkX, currentChunk.chunkZ)
        var chosenEntity: Entity? = null
        var chosenEntityDistance = Double.MAX_VALUE
        chunks.forEach { chunk ->
            val entities = instance.getChunkEntities(chunk)
            entities.forEach entityEach@ { ent ->

                if (!MobUtils.isValidTarget(ent, entityCreature)) {
                    return@entityEach
                }

                // Check distance
                val d = entityCreature.getDistance(ent)
                if ((chosenEntity == null || d < chosenEntityDistance) && d < range) {
                    chosenEntity = ent
                    chosenEntityDistance = d
                    return@entityEach
                }
            }
        }
        return chosenEntity
    }
}