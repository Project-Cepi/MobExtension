package world.cepi.mob.util

import net.minestom.server.command.CommandSender
import net.minestom.server.entity.*
import net.minestom.server.instance.Chunk
import net.minestom.server.instance.Instance
import net.minestom.server.item.Material
import net.minestom.server.tag.Tag
import net.minestom.server.utils.chunk.ChunkUtils
import world.cepi.kepi.messages.sendFormattedTranslatableMessage
import world.cepi.mob.mob.mobEgg
import java.util.*

object MobUtils {

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

    fun isValidTarget(target: Entity, entityCreature: EntityCreature): Boolean {
        // Only target living entities
        if (target !is LivingEntity) {
            return false
        }

        // Don't target itself
        if (target.uuid == entityCreature.uuid) {
            return false
        }

        if (target.isRemoved()) {
            // Entity not valid
            return false
        }

        if (target is Player && (target.isCreative || target.gameMode == GameMode.SPECTATOR)) {
            return false
        }

        if (target.getTag(Tag.Byte("dead")) == 0.toByte()) {
            return false
        }

        return true
    }

    /**
     * Checks if the sender has a mob egg.
     *
     * @param sender The sender to check
     *
     * @return If the sender has the egg or not (false if they don't)
     */
    fun hasMobEgg(sender: CommandSender): Boolean {
        if (sender !is Player) return false

        if (sender.itemInMainHand.material == Material.AIR) {
            sender.sendFormattedTranslatableMessage("item", "main.required")
            return false
        }

        if (sender.mobEgg == null) {
            sender.sendFormattedTranslatableMessage("mob", "egg.created.required")
            return false
        }

        return true
    }

}