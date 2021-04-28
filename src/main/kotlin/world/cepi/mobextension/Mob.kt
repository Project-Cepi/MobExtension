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
import world.cepi.kstom.item.clientData
import world.cepi.kstom.item.get
import world.cepi.kstom.item.item
import world.cepi.kstom.item.withMeta
import world.cepi.mobextension.goal.SerializableGoal
import world.cepi.mobextension.meta.MobMeta
import world.cepi.mobextension.targets.SerializableTarget
import kotlin.reflect.KClass

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

        properties.metas.values.forEach { it.apply(mob) }

        return mob

    }

    /** Generates an item that players can use to spawn the mob. */
    fun generateEgg(): ItemStack {

        val entityData = EntityData.findByType(this.type)!!

        val mobEgg = item(entityData.material, 1) {
            displayName(Component.text("${entityData.displayName} Spawn Egg", NamedTextColor.GOLD)
                .decoration(TextDecoration.ITALIC, false))

            lore(listOf(
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
            ))

            withMeta {
                clientData {
                    this[mobKey, module] = this@Mob
                }
            }
        }

        return mobEgg

    }

    val type: EntityType
        get() = this.properties.type

    @Serializable
    class Properties {

        val goals: MutableList<SerializableGoal> = mutableListOf()
        val metas: MutableMap<KClass<out MobMeta>, MobMeta> = mutableMapOf()
        val targets: MutableList<SerializableTarget> = mutableListOf()

        fun addMeta(vararg meta: MobMeta): Properties {
            meta.forEach { metas[it::class] = it }
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

    val mob = item.meta.get<Mob>(Mob.mobKey, module) ?: return

    val creature = mob.generateMob() ?: return
    creature.setInstance(event.player.instance!!, event.position.toPosition().clone().add(.0, 1.0, .0))
}

val Player.mob: Mob?
    get() {
        if (this.itemInMainHand.meta.get<Mob>(Mob.mobKey, module) == null) {
            return null
        }

        return this.itemInMainHand.meta.get(Mob.mobKey, module)
    }