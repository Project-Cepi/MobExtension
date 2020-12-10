package world.cepi.mobextension.conditional

import net.minestom.server.event.Event
import world.cepi.mobextension.conditional.mutations.Mutation
import kotlin.reflect.KClass

class Conditional(
    val eventClass: KClass<out Event>
) {
    private val conditions: MutableList<in Condition> = mutableListOf()
    private val mutations: MutableList<in Mutation> = mutableListOf()

    fun addCondition(condition: Condition): Conditional {
        conditions.add(condition)
        return this
    }

    fun addMutation(mutation: Mutation): Conditional {
        mutations.add(mutation)
        return this
    }

}