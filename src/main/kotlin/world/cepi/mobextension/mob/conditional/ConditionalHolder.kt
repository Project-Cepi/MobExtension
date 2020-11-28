package world.cepi.mobextension.mob.conditional

import net.minestom.server.entity.Entity
import net.minestom.server.event.Event
import world.cepi.mobextension.mob.Mob
import kotlin.reflect.KClass

open class ConditionalHolder {

    val conditions: MutableList<Conditional> = mutableListOf()

    fun addConditonal(event: KClass<out Event>, conditional: (Entity) -> Boolean, code: (Entity) -> Unit) {

    }

}