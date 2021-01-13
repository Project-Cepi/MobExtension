package world.cepi.mobextension.pathfinding.hierarchical

import net.minestom.server.instance.Instance
import net.minestom.server.instance.block.Block
import net.minestom.server.instance.block.BlockFace
import net.minestom.server.utils.BlockPosition

/** A block position that contains the possibility to move to other places. */
class Node(
        /** The place the [Node] is located at */
        val blockPosition: BlockPosition,

        /** The instance the [Node] is in. */
        val instance: Instance
) {

    /** What faces can the entity not walk on. */
    val facesBlocked: MutableList<BlockFace> = mutableListOf()

    /** Scans all blockfaces around it forcefully. */
    fun forcefulScan() {
        BlockFace.values().forEach {
            if (Block.fromStateId(instance.getBlockStateId(blockPosition.getRelative(it))).isAir)
                facesBlocked.add(it)
        }
    }
}