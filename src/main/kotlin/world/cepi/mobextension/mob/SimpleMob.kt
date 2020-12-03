package world.cepi.mobextension.mob

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import net.minestom.server.entity.EntityType
import world.cepi.mobextension.goal.serializable.SerializableGoal
import world.cepi.mobextension.mob.meta.NameMeta

/**
 * Represents a [Mob] that can be serialized from a JSON file
 * @param id The unique ID of a mob. Is not actually a UUID but needs to identify the mob uniquely
 * @param name The name of the mob that will be displayed above its head. Can be left blank
 * @param goalList List of [SerializableGoal] objects to be applied to the mob. Will be applied as [net.minestom.server.entity.ai.GoalSelector] to the entity on spawn
 * @param mobType The [EntityType] of the generated [net.minestom.server.entity.EntityCreature]
 * */
class SimpleMob(
    val id: String,
    val name: String = "",
    @SerialName("goals") val goalList: Array<SerializableGoal>, // goals would override a property in Mob()
    @SerialName("type") val mobType: String
) : Mob(MobProperties()
    .addMeta(NameMeta(name))
    .addGoal(*goalList)
    .setType(EntityType.valueOf(mobType.toUpperCase())))