package world.cepi.mobextension

import kotlinx.serialization.Serializable
import net.minestom.server.chat.ChatColor
import net.minestom.server.chat.ColoredText
import net.minestom.server.data.DataImpl
import net.minestom.server.entity.Entity
import net.minestom.server.entity.EntityCreature
import net.minestom.server.entity.EntityType
import net.minestom.server.entity.Player
import net.minestom.server.event.player.PlayerUseItemOnBlockEvent
import net.minestom.server.item.ItemStack
import world.cepi.mobextension.goal.SerializableGoal
import world.cepi.mobextension.meta.MobMeta
import world.cepi.mobextension.targets.SerializableTarget

/** The mob class that holds conditionals, meta, and goals. */
@Serializable
open class Mob(val properties: Properties) {

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
    fun generateMob(): EntityCreature? {

        val mobData = EntityData.findByType(this.type) ?: return null

        val mob = EntityCreature(mobData.type)

        mob.goalSelectors.addAll(properties.goals.map { it.toGoalSelector(mob) })
        mob.targetSelectors.addAll(properties.targets.map { it.toTarget(mob) })

        properties.metas.forEach { it.apply(mob) }

        return mob

    }

    /** Generates an item that players can use to spawn the mob. */
    fun generateEgg(): ItemStack {

        val entityData = EntityData.findByType(this.type)!!

        val mobEgg = ItemStack(entityData.material, 1)
        mobEgg.displayName = ColoredText.of(ChatColor.GOLD, "${entityData.displayName} Spawn Egg")
        val data = DataImpl()

        data.set(mobKey, this)

        mobEgg.data = data
        return mobEgg

    }

    val type: EntityType
        get() = this.properties.type

    @Serializable
    class Properties {

        val goals: MutableList<SerializableGoal> = mutableListOf()
        val metas: MutableSet<MobMeta> = mutableSetOf()
        val targets: MutableList<SerializableTarget> = mutableListOf()

        fun addMeta(vararg meta: MobMeta): Properties {
            meta.forEach { metas.add(it) }
            return this
        }

        fun addGoal(vararg goal: SerializableGoal): Properties {
            goal.forEach { goals.add(it) }
            return this
        }

        fun addTarget(vararg target: SerializableTarget): Properties {
            target.forEach { targets.add(it) }
            return this
        }

        var type: EntityType = EntityType.LLAMA

        fun setType(typeToSet: EntityType): Properties {
            type = typeToSet
            return this
        }
    }

}

fun mobSpawnEvent(event: PlayerUseItemOnBlockEvent) {
    val item = event.player.itemInMainHand
    if (item.data?.get<Mob>(Mob.mobKey) == null) return

    val mob = item.data!!.get<Mob>(Mob.mobKey)!!

    val creature = mob.generateMob() ?: return
    creature.setInstance(event.player.instance!!, event.position.toPosition().clone().add(.0, 1.0, .0))
}

val Player.mob: Mob?
    get() {
        if (this.itemInMainHand.data?.get<Mob>(Mob.mobKey) == null) {
            return null
        }

        return this.itemInMainHand.data?.get<Mob>(Mob.mobKey)
    }