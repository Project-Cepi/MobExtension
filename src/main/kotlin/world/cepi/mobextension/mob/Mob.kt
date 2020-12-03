package world.cepi.mobextension.mob

import net.minestom.server.chat.ChatColor
import net.minestom.server.chat.ColoredText
import net.minestom.server.data.DataImpl
import net.minestom.server.entity.Entity
import net.minestom.server.entity.EntityType
import net.minestom.server.event.player.PlayerBlockInteractEvent
import net.minestom.server.item.ItemStack
import net.minestom.server.item.Material
import net.minestom.server.utils.Position
import world.cepi.mobextension.goal.serializable.SerializableGoal
import world.cepi.mobextension.mob.conditional.Conditional
import world.cepi.mobextension.mob.meta.MobMeta
import kotlin.reflect.full.primaryConstructor

/** The mob class that holds conditionals, meta, and goals. */
open class Mob(val properties: MobProperties) {

    companion object {
        /** The string used for storing data inside items. */
        const val mobKey = "mob-key"
    }

    /**
     * Creates an entity that is spawnable, containing all the behavior necessary to be spawned.
     *
     * @param position The position the mob should be spawned at
     *
     * @return an [Entity] object; If the entity was not able to be generated, it will be null.
     *
     */

    val mob = mobTypeList.first { it.second == properties.type }.let { entityClassPair ->
        entityClassPair.first.primaryConstructor!!.call(Position(0f, 0f, 0f))
    }

    fun generateMob(position: Position): Entity? {
        mobTypeList.firstOrNull { it.second == properties.type }?.let { entityClassPair ->
            return entityClassPair.first.primaryConstructor!!.call(position)
        }

        return null

    }

    /** Generates an item that players can use to spawn the mob. */
    fun generateEgg(): ItemStack {

        val mobEgg = ItemStack(Material.LLAMA_SPAWN_EGG, 1)
        mobEgg.displayName = ColoredText.of(ChatColor.GOLD, "Mob Creation Egg")
        val data = DataImpl()

        data.set(mobKey, this)

        mobEgg.data = data
        return mobEgg

    }

    class MobProperties {

        val conditions: MutableList<Conditional> = mutableListOf()
        val goals: MutableList<SerializableGoal> = mutableListOf()
        val metas: MutableList<MobMeta> = mutableListOf()

        fun addMeta(meta: MobMeta): MobProperties {
            metas.add(meta)
            return this
        }

        fun addGoal(vararg goal: SerializableGoal): MobProperties {
            goal.forEach { goals.add(it) }
            return this
        }

        fun addConditional(conditional: Conditional): MobProperties {
            conditions.add(conditional)
            return this
        }

        lateinit var type: EntityType

        fun setType(typeToSet: EntityType): MobProperties {
            type = typeToSet
            return this
        }
    }

}

fun mobSpawnEvent(event: PlayerBlockInteractEvent) {
    val item = event.player.itemInMainHand
    if (item.data?.get<Mob>(Mob.mobKey) == null) return

    val mobData = item.data?.get<Mob>(Mob.mobKey)

    val mobEntity = mobData!!.generateMob(event.blockPosition.add(0, 1, 0).toPosition())
    mobEntity!!.spawn()
}