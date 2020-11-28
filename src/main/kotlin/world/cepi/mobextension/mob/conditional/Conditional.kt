package world.cepi.mobextension.mob.conditional

import net.minestom.server.event.Event
import world.cepi.mobextension.mob.conditional.mutations.Mutation
import kotlin.reflect.KClass

class Conditional(
    val eventClass: KClass<out Event>
) {
    val conditions: MutableList<out Condition> = mutableListOf()
    val mutations: MutableList<out Mutation> = mutableListOf()
}