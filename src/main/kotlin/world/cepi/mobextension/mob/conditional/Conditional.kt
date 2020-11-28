package world.cepi.mobextension.mob.conditional

import net.minestom.server.event.Event
import world.cepi.mobextension.mob.conditional.mutations.Mutation
import kotlin.reflect.KClass

class Conditional(
    val eventClass: KClass<out Event>
) {
    private val conditions: MutableList<in Condition> = mutableListOf()
    private val mutations: MutableList<in Mutation> = mutableListOf()

    fun addCondition(condition: Condition) {
        conditions.add(condition)
    }

    fun addMutation(mutation: Mutation) {
        mutations.add(mutation)
    }

}