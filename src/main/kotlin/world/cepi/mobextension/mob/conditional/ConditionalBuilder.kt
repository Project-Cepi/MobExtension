package world.cepi.mobextension.mob.conditional

import net.minestom.server.event.Event
import kotlin.reflect.KClass

class ConditionalBuilder(val eventClass: KClass<out Event>) {

    val conditional: Conditional = Conditional(eventClass)

    fun build(): Conditional {
        return conditional
    }

}