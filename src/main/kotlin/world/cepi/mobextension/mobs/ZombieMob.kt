package world.cepi.mobextension.mobs

import net.minestom.server.entity.EntityType
import world.cepi.mobextension.goal.SerializableGoals
import world.cepi.mobextension.Mob
import world.cepi.mobextension.meta.HealthMeta
import world.cepi.mobextension.meta.NameMeta

object ZombieMob: Mob(Mob.Properties()
        .addMeta(HealthMeta(20f))
        .addMeta(NameMeta("Zombie"))
        .addGoal(SerializableGoals.RandomStrollGoal(5))
        .setType(EntityType.ZOMBIE))