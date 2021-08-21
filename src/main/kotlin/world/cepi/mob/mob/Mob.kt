package world.cepi.mob.mob

import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.TextComponent
import net.kyori.adventure.text.format.NamedTextColor
import net.kyori.adventure.text.format.TextDecoration
import net.minestom.server.entity.Entity
import net.minestom.server.entity.EntityCreature
import net.minestom.server.entity.EntityType
import net.minestom.server.entity.Player
import net.minestom.server.item.ItemStack
import org.checkerframework.checker.nullness.qual.NonNull
import world.cepi.kstom.item.*
import world.cepi.mob.goal.SerializableGoal
import world.cepi.mob.meta.MobMeta
import world.cepi.mob.targets.SerializableTarget
import kotlin.reflect.KClass

/** The mob class that holds conditionals, meta, and goals. */
@Serializable
open class Mob(
    val goals: MutableList<SerializableGoal> = mutableListOf(),
    @Serializable(with = MobMetaMapSerializer::class)
    val metaMap: MutableMap<KClass<out MobMeta>, MobMeta> = mutableMapOf(),
    val targets: MutableList<SerializableTarget> = mutableListOf(),
    var type: EntityType = EntityType.LLAMA
) {

    companion object {
        /** The string used for storing data inside items. */
        const val mobKey = "mob-key"

        val format = Json {
            prettyPrint = true
            ignoreUnknownKeys = true
            coerceInputValues = true
            isLenient = true
        }

        fun fromJSON(json: String): Mob = format.decodeFromString(json)
    }

    fun toJSON(): String {
        return format.encodeToString(this)
    }

    /**
     * Creates an entity that is spawnable, containing all the behavior necessary to be spawned.
     *
     * @return an [Entity] object; If the entity was not able to be generated, it will be null.
     *
     */
    fun generateMob(): EntityCreature? {

        // Get the mob data class
        val mobData = EntityEggData.findByType(this.type) ?: return null

        val mob = EntityCreature(mobData.type)

        mob.addAIGroup(
            goals.map { it.toGoalSelector(mob) },
            targets.map { it.toTarget(mob) }
        )

        metaMap.values.forEach { it.apply(mob) }

        return mob

    }

    /** Generates an item that players can use to spawn the mob. */
    fun generateEgg(currentItem: ItemStack = ItemStack.AIR): ItemStack {

        val entityData = EntityEggData.findByType(this.type)!!

        return ItemStack.fromNBT(entityData.material, currentItem.meta.toNBT()).and {
            displayName(
                Component.text("${entityData.displayName} Spawn Egg", entityData.color)
                    .decoration(TextDecoration.ITALIC, false)
            )

            lore(
                listOf<@NonNull TextComponent>(
                    Component.space(),
                    Component.text("Goals: ", NamedTextColor.GRAY)
                        .append(Component.text(goals.size, NamedTextColor.WHITE))
                        .decoration(TextDecoration.ITALIC, false),
                    Component.text("Meta: ", NamedTextColor.GRAY)
                        .append(Component.text(metaMap.size, NamedTextColor.WHITE))
                        .decoration(TextDecoration.ITALIC, false),
                    Component.text("Targets: ", NamedTextColor.GRAY)
                        .append(Component.text(targets.size, NamedTextColor.WHITE))
                        .decoration(TextDecoration.ITALIC, false),
                    Component.space(),
                    Component.text("* ", NamedTextColor.DARK_GRAY)
                        .append(Component.text("Left click to open mob UI", NamedTextColor.GRAY)),
                    Component.text("* ", NamedTextColor.DARK_GRAY)
                        .append(Component.text("Right click to spawn entity", NamedTextColor.GRAY))
                )
            )

            withMeta {
                this[mobKey] = this@Mob
            }
        }

    }


    fun addMeta(vararg meta: MobMeta): Mob {
        meta.forEach { metaMap[it::class] = it }
        return this
    }

    fun addGoal(vararg goal: SerializableGoal): Mob {
        goals.addAll(goal)
        return this
    }

    fun addTarget(vararg target: SerializableTarget): Mob {
        targets.addAll(target)
        return this
    }


}

val Player.mobEgg: Mob?
    get() = this.itemInMainHand.meta.get(Mob.mobKey)

val Player.mobEggOffHand: Mob?
    get() = this.itemInOffHand.meta.get(Mob.mobKey)