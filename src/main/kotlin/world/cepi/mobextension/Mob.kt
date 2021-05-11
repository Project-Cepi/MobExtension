package world.cepi.mobextension

import kotlinx.serialization.Serializable
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.TextComponent
import net.kyori.adventure.text.format.NamedTextColor
import net.kyori.adventure.text.format.TextDecoration
import net.minestom.server.entity.Entity
import net.minestom.server.entity.EntityCreature
import net.minestom.server.entity.EntityType
import net.minestom.server.entity.Player
import net.minestom.server.event.player.PlayerUseItemOnBlockEvent
import net.minestom.server.item.ItemStack
import org.checkerframework.checker.nullness.qual.NonNull
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

        return item(entityData.material, 1) {
            displayName(
                Component.text("${entityData.displayName} Spawn Egg", NamedTextColor.GOLD)
                    .decoration(TextDecoration.ITALIC, false)
            )

            lore(
                listOf<@NonNull TextComponent>(
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
            )

            withMeta {
                clientData {
                    this[mobKey] = this@Mob.asSerializable()
                }
            }
        }

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

fun mobSpawnEvent(event: PlayerUseItemOnBlockEvent) = with(event) {
    val mob = player.mobEgg ?: return

    val creature = mob.generateMob() ?: return

    creature.setInstance(
        player.instance!!,
        // don't spawn the entity in the block
        event.position.toPosition().clone().add(.0, 1.0, .0)
    )
}

val Player.mobEgg: Mob?
    get() = this.itemInMainHand.meta.get<SerializableMob>(Mob.mobKey)?.toMob()