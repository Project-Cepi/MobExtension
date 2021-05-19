package world.cepi.mob.targets

import net.minestom.server.instance.Chunk
import net.minestom.server.instance.Instance
import net.minestom.server.utils.chunk.ChunkUtils
import java.util.*

internal object TargetUtils {

    private val posX = intArrayOf(1, 0, -1)
    private val posZ = intArrayOf(1, 0, -1)

    fun getNeighbours(instance: Instance, chunkX: Int, chunkZ: Int): List<Chunk?> {
        val chunks: MutableList<Chunk?> = ArrayList()
        // Constants used to loop through the neighbors
        for (x in posX) for (z in posZ) {
            val targetX = chunkX + x
            val targetZ = chunkZ + z
            val chunk = instance.getChunk(targetX, targetZ)
            if (ChunkUtils.isLoaded(chunk)) {
                // Chunk is loaded, add it
                chunks.add(chunk)
            }
        }

        return chunks
    }

}