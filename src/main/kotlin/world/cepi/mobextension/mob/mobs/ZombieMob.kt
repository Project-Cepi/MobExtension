package world.cepi.mobextension.mob.mobs

import net.minestom.server.entity.EntityType
import net.minestom.server.entity.ai.goal.RandomStrollGoal
import world.cepi.mobextension.mob.Mob
import world.cepi.mobextension.mob.meta.HealthMeta
import world.cepi.mobextension.mob.meta.NameMeta

object ZombieMob: Mob() {
    init {
        this.addMeta(HealthMeta(20f))
                .addMeta(NameMeta("Zombie"))
                .addGoal(RandomStrollGoal(this.mob, 5))
                .setType(EntityType.ZOMBIE)
    }
}