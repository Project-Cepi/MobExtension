package world.cepi.mob.data

import kotlinx.serialization.Serializable
import world.cepi.mob.mob.Mob

@Serializable
class RegisteredMob(val mobKey: String, val mob: Mob)