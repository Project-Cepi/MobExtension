package world.cepi.mobextension.mob

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import net.minestom.server.entity.EntityType
import world.cepi.mobextension.goal.SerializableGoal
import world.cepi.mobextension.meta.MobMeta
import world.cepi.mobextension.targets.SerializableTarget

/** A simpler form of a mob containing pure primitives */
@Serializable
data class SerializableMob(
    @SerialName("goals") val goalList: List<SerializableGoal>?,
    @SerialName("meta") val metaList: List<MobMeta>?,
    @SerialName("targets") val targetList: List<SerializableTarget>?,
    @SerialName("type") val mobType: String?
) {
    /**
     * Turns a [SerializableMob] to a [Mob]
     *
     * @return The [Mob] converted from a [SerializableMob]
     */
    fun toMob(): Mob = Mob().apply {
        addGoal(*goalList?.toTypedArray() ?: emptyArray())
        addMeta(*metaList?.toTypedArray() ?: emptyArray())
        addTarget(*targetList?.toTypedArray() ?: emptyArray())
        type = EntityType.values().firstOrNull { it.name.equals(mobType, ignoreCase = true) } ?: EntityType.LLAMA
    }

    companion object {

        val format = Json {
            prettyPrint = true
            ignoreUnknownKeys = true
            coerceInputValues = true
            isLenient = true
        }

        fun fromJSON(json: String): SerializableMob {
            return format.decodeFromString(json)
        }
    }

    fun toJSON(): String {
        return format.encodeToString(this)
    }

}

fun Mob.asSerializable(): SerializableMob = SerializableMob(
    this.goals,
    this.metas.values.toList(),
    this.targets,
    this.type.toString()
)