package world.cepi.mobextension.pathfinding.hierarchical

import net.minestom.server.instance.Instance
import net.minestom.server.instance.block.Block
import net.minestom.server.instance.block.BlockFace
import net.minestom.server.utils.BlockPosition

/** A fancy block position that contains the possibility to move to other places. */
class Node(
        val blockPosition: BlockPosition,
        val instance: Instance
) {

    val facesBlocked: MutableList<BlockFace> = mutableListOf()

    /** Scans all blockfaces around it forcefully. */
    fun forcefulScan() {
        BlockFace.values().forEach {
            if (Block.fromStateId(instance.getBlockStateId(blockPosition.getRelative(it))).isAir)
                facesBlocked.add(it)
        }
    }
}