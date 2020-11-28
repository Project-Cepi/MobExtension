package world.cepi.mobextension.mob.conditional

import net.minestom.server.event.Event
import world.cepi.mobextension.mob.conditional.mutations.Mutation
import kotlin.reflect.KClass

class ConditionalBuilder(
    /** The event to listen to for this conditional */
    eventClass: KClass<out Event>
) {

    /** The internal conditional used to modify properties. The builder is a builder wrapper for this. */
    private val conditional: Conditional = Conditional(eventClass)

    fun addCondition(condition: Condition): ConditionalBuilder {
        conditional.addCondition(condition)
        return this
    }

    fun addMutation(mutation: Mutation): ConditionalBuilder {
        conditional.addMutation(mutation)
        return this
    }

    fun build(): Conditional {
        return conditional
    }

}