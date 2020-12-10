package world.cepi.mobextension.conditional

import world.cepi.mobextension.Mob

class Condition(val condition: (Mob) -> Boolean)