package world.cepi.mobextension.api.events

import net.minestom.server.event.Event
import kotlin.reflect.KClass

@Target(AnnotationTarget.FUNCTION)
annotation class EventHandler(val event: KClass<out Event>)
