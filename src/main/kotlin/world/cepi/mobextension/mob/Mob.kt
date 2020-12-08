package world.cepi.mobextension.mob

import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient
import net.minestom.server.chat.ChatColor
import net.minestom.server.chat.ColoredText
import net.minestom.server.data.DataImpl
import net.minestom.server.entity.Entity
import net.minestom.server.entity.EntityCreature
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
@Serializable
open class Mob(val properties: Properties) {

    companion object {
        /** The string used for storing data inside items. */
        @Transient
        const val mobKey = "mob-key"

        @Transient
        val registry = mutableListOf<Mob>()

        fun register(mob: Mob) {
            if (!registry.contains(mob)) registry.add(mob)
        }

        fun getById(id: String): Mob? = registry.firstOrNull { it.properties.id == id }
    }

    /**
     * Creates an entity that is spawnable, containing all the behavior necessary to be spawned.
     *
     * @param position The position the mob should be spawned at
     *
     * @return an [Entity] object; If the entity was not able to be generated, it will be null.
     *
     */

    @Transient
    val mob: EntityCreature? = mobTypeList.first { it.second == properties.type }.let { entityClassPair ->
        entityClassPair.first.java.constructors[0]?.newInstance(Position(0f, 0f, 0f)) as? EntityCreature
    }

    fun generateMob(position: Position): Entity? {
        val mobClassPair = mobTypeList.firstOrNull { it.second == properties.type } ?: return null
        
        val mob: EntityCreature = mobClassPair.first.primaryConstructor!!.call(position)

        mob.goalSelectors.addAll(properties.goals.map { it.toGoalSelector(mob) })
        properties.metas.forEach { it.apply(mob) }

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

    val type: EntityType
        get() = this.properties.type

    @Serializable
    class Properties {

        @Transient
        val conditions: MutableList<Conditional> = mutableListOf()
        val goals: MutableList<SerializableGoal> = mutableListOf()
        val metas: MutableList<MobMeta> = mutableListOf()

        fun addMeta(vararg meta: MobMeta): Properties {
            meta.forEach { metas.add(it) }
            return this
        }

        fun addGoal(vararg goal: SerializableGoal): Properties {
            goal.forEach { goals.add(it) }
            return this
        }

        fun addConditional(vararg conditional: Conditional): Properties {
            conditional.forEach { conditions.add(it) }
            return this
        }

        var type: EntityType = EntityType.LLAMA

        fun setType(typeToSet: EntityType): Properties {
            type = typeToSet
            return this
        }

        var id: String? = null
        fun setMobId(idToSet: String): Properties { this.id = idToSet; return this }
    }

}

fun mobSpawnEvent(event: PlayerBlockInteractEvent) {
    val item = event.player.itemInMainHand
    if (item.data?.get<Mob>(Mob.mobKey) == null) return

    val mobData = item.data?.get<Mob>(Mob.mobKey)

    val mobEntity = mobData!!.generateMob(event.blockPosition.add(0, 1, 0).toPosition())
    mobEntity!!.spawn()
}