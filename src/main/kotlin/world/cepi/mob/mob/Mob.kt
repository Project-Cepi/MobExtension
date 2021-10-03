package world.cepi.mob.mob

import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import net.kyori.adventure.key.Key
import net.kyori.adventure.sound.Sound
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.TextComponent
import net.kyori.adventure.text.format.NamedTextColor
import net.kyori.adventure.text.format.TextDecoration
import net.minestom.server.coordinate.Point
import net.minestom.server.coordinate.Vec
import net.minestom.server.entity.*
import net.minestom.server.entity.damage.EntityDamage
import net.minestom.server.event.EventFilter
import net.minestom.server.event.EventNode
import net.minestom.server.event.entity.EntityDamageEvent
import net.minestom.server.event.entity.EntityDeathEvent
import net.minestom.server.event.trait.EntityEvent
import net.minestom.server.instance.Instance
import net.minestom.server.item.ItemStack
import net.minestom.server.sound.SoundEvent
import org.checkerframework.checker.nullness.qual.NonNull
import world.cepi.kstom.event.listenOnly
import world.cepi.kstom.item.*
import world.cepi.kstom.serializer.EntityTypeSerializer
import world.cepi.kstom.util.playSound
import world.cepi.mob.goal.SerializableGoal
import world.cepi.mob.meta.MobMeta
import world.cepi.mob.mob.player.PlayerMob
import world.cepi.mob.property.MobProperty
import world.cepi.mob.property.NameProperty
import world.cepi.mob.targets.SerializableTarget
import java.util.*
import kotlin.reflect.KClass

/** The mob class that holds conditionals, meta, and goals. */
@Serializable
open class Mob(
    val goals: MutableList<SerializableGoal> = mutableListOf(),
    @Serializable(with = MobMetaMapSerializer::class)
    val metaMap: MutableMap<KClass<out MobMeta>, MobMeta> = mutableMapOf(),
    @Serializable(with = MobPropertyMapSerializer::class)
    val propertyMap: MutableMap<KClass<out MobProperty>, MobProperty> = mutableMapOf(),
    val targets: MutableList<SerializableTarget> = mutableListOf(),
    @Serializable(with = EntityTypeSerializer::class)
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

        val mobEventNode = EventNode.type("MobSystem", EventFilter.ENTITY)

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
    fun generateMob(): GeneratedMob? {

        // Get the mob data class
        val mobData = EntityEggData.findByType(this.type) ?: return null

        val mob = if (mobData.type == EntityType.PLAYER)
            PlayerMob(mobData.type, property<NameProperty>()?.name ?: "Mob")
        else
            EntityCreature(mobData.type)

        mob.addAIGroup(
            goals.map { it.toGoalSelector(mob) },
            targets.map { it.toTarget(mob) }
        )

        // Apply meta and properties
        metaMap.values.forEach { it.apply(mob) }
        propertyMap.values.forEach { it.apply(mob) }

        val node = EventNode.type("MobSystemMob-${mob.uuid}", EventFilter.ENTITY)

        node.listenOnly<EntityDamageEvent> {
            entity.viewers.forEach {
                it.playSound(
                    Sound.sound(
                        Key.key("minecraft:entity.${entity.entityType.namespace().path}.hurt"),
                        Sound.Source.NEUTRAL,
                        1f,
                        1f
                    ), this.entity
                )
            }
        }

        node.listenOnly<EntityDeathEvent> {
            val player = (((entity as? LivingEntity)?.lastDamageSource as? EntityDamage)?.source as? Player)

            player?.playSound(
                Sound.sound(SoundEvent.ENTITY_EXPERIENCE_ORB_PICKUP, Sound.Source.AMBIENT, .5f, 2f),
                player.position
            )

            entity.viewers.forEach {
                it.playSound(
                    Sound.sound(
                        Key.key("minecraft:entity.${entity.entityType.namespace().path}.death"),
                        Sound.Source.NEUTRAL,
                        1f,
                        1f
                    ), this.entity
                )
            }
        }

        mobEventNode.addChild(node)

        return GeneratedMob(mobEventNode, mob)

    }

    fun spawnMob(instance: Instance, position: Point = Vec.ZERO) {
        val generatedMob = generateMob()

        generatedMob?.mob?.setInstance(instance, position)
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
                    Component.text("Properties: ", NamedTextColor.GRAY)
                        .append(Component.text(propertyMap.size, NamedTextColor.WHITE))
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

    fun add(vararg meta: MobMeta): Mob {
        meta.forEach { metaMap[it::class] = it }
        return this
    }

    fun <T : MobMeta> meta(meta: KClass<out T>): T? =
        metaMap[meta] as? T

    inline fun <reified T : MobMeta> meta(): T? =
        meta(T::class)

    @JvmName("removeMeta")
    fun remove(vararg meta: KClass<out MobMeta>): Mob {
        metaMap.filterTo(metaMap) { !meta.contains(it.key) }
        return this
    }

    fun add(vararg properties: MobProperty): Mob {
        properties.forEach { propertyMap[it::class] = it }
        return this
    }

    fun <T : MobProperty> property(property: KClass<out T>): T? =
        propertyMap[property] as? T

    inline fun <reified T : MobProperty> property(): T? =
        property(T::class)

    @JvmName("removeProperty")
    fun remove(vararg property: KClass<out MobProperty>): Mob {
        propertyMap.filterTo(propertyMap) { !property.contains(it.key) }
        return this
    }

    fun add(vararg goal: SerializableGoal): Mob {
        goals.addAll(goal)
        return this
    }

    fun add(vararg target: SerializableTarget): Mob {
        targets.addAll(target)
        return this
    }


}

data class GeneratedMob(
    val eventNode: EventNode<EntityEvent>,
    val mob: EntityCreature
)

val Player.mobEgg: Mob?
    get() = this.itemInMainHand.meta.get(Mob.mobKey)

val Player.mobEggOffHand: Mob?
    get() = this.itemInOffHand.meta.get(Mob.mobKey)

fun mob(type: EntityType = EntityType.LLAMA, mob: Mob.() -> Unit) = Mob(type = type).apply(mob)