package world.cepi.mobextension.mob

import kotlinx.serialization.SerialName
import net.minestom.server.entity.EntityType
import world.cepi.mobextension.goal.serializable.SerializableGoal
import world.cepi.mobextension.mob.meta.MobMeta

class SerializableMob(
        @SerialName("goals") val goalList: Array<SerializableGoal>,
        @SerialName("meta") val metaList: Array<MobMeta>,
        @SerialName("type") val mobType: EntityType
) {
    fun toMob(): Mob {
        return Mob(
                Mob.Properties()
                        .addGoal(*goalList)
                        .addMeta(*metaList)
                        .setType(mobType)
        )
    }
}

fun Mob.asSerializable(): SerializableMob = SerializableMob(this.goals, this.meta, this.type)