package world.cepi.mobextension.conditional.mutations

import world.cepi.mobextension.Mob

/** This modifies a property of a mob, whether its speed or armor. */
class Mutation(val mutation: (Mob) -> Unit)