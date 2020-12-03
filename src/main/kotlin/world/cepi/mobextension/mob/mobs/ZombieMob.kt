package world.cepi.mobextension.mob.mobs

import net.minestom.server.entity.EntityType
import world.cepi.mobextension.goal.serializable.SerializableGoals
import world.cepi.mobextension.mob.Mob
import world.cepi.mobextension.mob.meta.HealthMeta
import world.cepi.mobextension.mob.meta.NameMeta

object ZombieMob: Mob(MobProperties()
        .addMeta(HealthMeta(20f))
        .addMeta(NameMeta("Zombie"))
        .addGoal(SerializableGoals.RandomStrollGoal(5))
        .setType(EntityType.ZOMBIE))