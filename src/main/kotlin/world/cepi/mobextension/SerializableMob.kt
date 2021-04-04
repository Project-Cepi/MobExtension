package world.cepi.mobextension

import kotlinx.serialization.*
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
        @SerialName("targets") val targets: List<SerializableTarget>?,
        @SerialName("type") val mobType: String?
) {
    /**
     * Turns a [SerializableMob] to a [Mob]
     *
     * @return The [Mob] converted from a [SerializableMob]
     */
    fun toMob(): Mob {
        return Mob(
                Mob.Properties()
                        .addGoal(*goalList?.toTypedArray() ?: arrayOf())
                        .addMeta(*metaList?.toTypedArray() ?: arrayOf())
                        .addTarget(*targets?.toTypedArray() ?: arrayOf())
                        .setType(EntityType.values().firstOrNull { it.name.equals(mobType, ignoreCase = true) } ?: EntityType.LLAMA)
        )
    }

    companion object {
        @Contextual
        val format = Json {
            prettyPrint = true
            ignoreUnknownKeys = true
            coerceInputValues = true
            isLenient = true
            serializersModule = module
        }

        fun fromJSON(json: String): SerializableMob {
            return format.decodeFromString(json)
        }
    }

    fun toJSON(): String {
        return format.encodeToString(this)
    }

}

fun Mob.asSerializable(): SerializableMob = SerializableMob(this.properties.goals, this.properties.metas.values.toList(), this.properties.targets, this.type.toString())