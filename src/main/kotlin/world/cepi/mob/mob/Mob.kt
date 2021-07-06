package world.cepi.mob.mob

import kotlinx.serialization.Serializable
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
    val metas: MutableMap<KClass<out MobMeta>, MobMeta> = mutableMapOf(),
    val targets: MutableList<SerializableTarget> = mutableListOf(),
    var type: EntityType = EntityType.LLAMA
) {

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
            goals.map { it.toGoalSelector(mob) },
            targets.map { it.toTarget(mob) }
        )

        metas.values.forEach { it.apply(mob) }

        return mob

    }

    /** Generates an item that players can use to spawn the mob. */
    fun generateEgg(currentItem: ItemStack = ItemStack.AIR): ItemStack {

        val entityData = EntityData.findByType(this.type)!!

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
                        .append(Component.text(metas.size, NamedTextColor.WHITE))
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
                clientData {
                    this[mobKey] = this@Mob.asSerializable()
                }
            }
        }

    }


    fun addMeta(vararg meta: MobMeta): Mob {
        meta.forEach { metas[it::class] = it }
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
    get() = this.itemInMainHand.meta.get<SerializableMob>(Mob.mobKey)?.toMob()