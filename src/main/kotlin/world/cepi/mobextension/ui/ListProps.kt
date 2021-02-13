package world.cepi.mobextension.ui

import com.mattworzala.canvas.Props
import com.mattworzala.canvas.RenderContext
import com.mattworzala.canvas.Slot
import com.mattworzala.canvas.component

class ListProps(
    var slots: MutableList<Slot.() -> Unit> = mutableListOf()
) : Props()

fun RenderContext<*>.list(index: Int, propHandler: ListProps.() -> Unit = {}) =
    child(index, SingleItemFromProps, ListProps(), propHandler)

@JvmField
val SingleItemFromProps = component<ListProps>(9, 6) {
    props.slots.forEachIndexed { index, slot ->
        val slotAtIndex = get(index)
        slot.invoke(slotAtIndex)
    }
}