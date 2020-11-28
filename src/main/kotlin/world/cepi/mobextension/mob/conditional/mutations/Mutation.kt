package world.cepi.mobextension.mob.conditional.mutations

import world.cepi.mobextension.mob.Mob

/** This modifies a property of a mob, whether its speed or armor. */
class Mutation(val mutation: (Mob) -> Unit)