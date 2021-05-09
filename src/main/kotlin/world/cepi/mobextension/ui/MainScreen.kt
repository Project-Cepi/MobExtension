package world.cepi.mobextension.ui

import com.mattworzala.canvas.Canvas
import com.mattworzala.canvas.CanvasProvider.canvas
import com.mattworzala.canvas.fragment
import net.minestom.server.item.Material
import world.cepi.kstom.adventure.asMini


val MainScreen = fragment(9, 1) {

    this[1].item(Material.NAME_TAG) {
        displayName("<yellow>Meta".asMini())
    }

    this[3].item(Material.NETHER_STAR) {
        displayName("<gold>Goals".asMini())
    }

    this[5].item(Material.TARGET) {
        displayName("<green>Targets".asMini())
    }

    this[7].item(Material.GHAST_SPAWN_EGG) {
        displayName("<gray>Type".asMini())
    }

    this[7].onClick { event ->
        val canvas: Canvas = canvas(event.player)
        canvas.render(TypeScreen)
    }
}