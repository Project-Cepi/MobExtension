package world.cepi.mobextension.ui

import com.mattworzala.canvas.Props
import com.mattworzala.canvas.component
import world.cepi.mobextension.EntityData

val TypeScreen = component<Props>(9, 6) {

    EntityData.mobTypeList.forEachIndexed { index, item ->
        slot(index) {
            item {
                this.material = item.material
            }

            onClick {

            }
        }
    }

}
