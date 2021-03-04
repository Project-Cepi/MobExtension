package net.minestom.server.entity.ai.target

import net.minestom.server.entity.Entity
import net.minestom.server.entity.EntityCreature
import net.minestom.server.entity.GameMode
import net.minestom.server.entity.Player
import net.minestom.server.entity.ai.TargetSelector
import net.minestom.server.instance.Chunk
import net.minestom.server.instance.Instance
import net.minestom.server.utils.chunk.ChunkUtils
import java.util.*

/**
 * Target the closest targetable entity (based on the class array)
 */
class ClosestPlayerTarget(entityCreature: EntityCreature, private val range: Float) : TargetSelector(entityCreature) {
    override fun findTarget(): Entity? {
        val instance = getEntityCreature().instance
        val currentChunk = instance!!.getChunkAt(entityCreature.position) ?: return null
        val chunks = getNeighbours(instance, currentChunk.chunkX, currentChunk.chunkZ)
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

    private fun getNeighbours(instance: Instance, chunkX: Int, chunkZ: Int): List<Chunk?> {
        val chunks: MutableList<Chunk?> = ArrayList()
        // Constants used to loop through the neighbors
        val posX = intArrayOf(1, 0, -1)
        val posZ = intArrayOf(1, 0, -1)
        for (x in posX) {
            for (z in posZ) {
                val targetX = chunkX + x
                val targetZ = chunkZ + z
                val chunk = instance.getChunk(targetX, targetZ)
                if (ChunkUtils.isLoaded(chunk)) {
                    // Chunk is loaded, add it
                    chunks.add(chunk)
                }
            }
        }
        return chunks
    }
}