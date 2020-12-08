package world.cepi.mobextension.mob

import kotlinx.serialization.*
import kotlinx.serialization.json.Json
import net.minestom.server.entity.EntityType
import world.cepi.mobextension.goal.serializable.SerializableGoal
import world.cepi.mobextension.mob.meta.MobMeta

/** A simpler form of a mob containing pure primitives */
@Serializable
data class SerializableMob(
        @SerialName("goals") val goalList: List<SerializableGoal>,
        @SerialName("meta") val metaList: List<MobMeta>,
        @SerialName("type") val mobType: String
) {
    fun toMob(): Mob {
        return Mob(
                Mob.Properties()
                        .addGoal(*goalList.toTypedArray())
                        .addMeta(*metaList.toTypedArray())
                        .setType(EntityType.values().first { it.name.equals(mobType, ignoreCase = true) })
        )
    }

    companion object {
        @Contextual
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

fun Mob.asSerializable(): SerializableMob = SerializableMob(this.goals.toList(), this.meta.toList(), this.type.toString())