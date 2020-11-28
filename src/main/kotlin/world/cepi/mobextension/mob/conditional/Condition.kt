package world.cepi.mobextension.mob.conditional

import world.cepi.mobextension.mob.Mob

class Condition(val condition: (Mob) -> Boolean)