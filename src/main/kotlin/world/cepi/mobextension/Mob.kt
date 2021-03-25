package world.cepi.mobextension

import kotlinx.serialization.Serializable
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import net.kyori.adventure.text.format.TextDecoration
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

        mob.addAIGroup(
            properties.goals.map { it.toGoalSelector(mob) },
            properties.targets.map { it.toTarget(mob) }
        )

        properties.metas.forEach { it.apply(mob) }

        return mob

    }

    /** Generates an item that players can use to spawn the mob. */
    fun generateEgg(): ItemStack {

        val entityData = EntityData.findByType(this.type)!!

        val mobEgg = ItemStack(entityData.material, 1)

        mobEgg.displayName = Component.text("${entityData.displayName} Spawn Egg", NamedTextColor.GOLD)
            .decoration(TextDecoration.ITALIC, false)

        mobEgg.lore = listOf(
            Component.space(),
            Component.text("Goals: ", NamedTextColor.GRAY)
                .append(Component.text(properties.goals.size, NamedTextColor.WHITE))
                .decoration(TextDecoration.ITALIC, false),
            Component.text("Meta: ", NamedTextColor.GRAY)
                .append(Component.text(properties.metas.size, NamedTextColor.WHITE))
                .decoration(TextDecoration.ITALIC, false),
            Component.text("Targets: ", NamedTextColor.GRAY)
                .append(Component.text(properties.targets.size, NamedTextColor.WHITE))
                .decoration(TextDecoration.ITALIC, false)
        )

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