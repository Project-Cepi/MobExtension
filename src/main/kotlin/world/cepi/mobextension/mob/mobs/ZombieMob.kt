package world.cepi.mobextension.mob.mobs

import world.cepi.mobextension.mob.Mob
import world.cepi.mobextension.mob.MobProperties
import world.cepi.mobextension.mob.meta.HealthMeta
import world.cepi.mobextension.mob.meta.NameMeta

class ZombieMob: Mob(
    MobProperties()
            .addMeta(HealthMeta(20f))
            .addMeta(NameMeta("Zombie"))
)