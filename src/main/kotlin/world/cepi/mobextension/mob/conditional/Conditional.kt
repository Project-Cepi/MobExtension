package world.cepi.mobextension.mob.conditional

import jdk.jfr.Event
import kotlin.reflect.KClass

class Conditional(
    val eventClass: KClass<out Event>
)