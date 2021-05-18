package world.cepi.mob.ui

import com.mattworzala.canvas.Canvas
import com.mattworzala.canvas.CanvasProvider.canvas
import com.mattworzala.canvas.fragment
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import net.minestom.server.item.Material


val MainScreen = fragment(9, 1) {

    this[1].item(Material.NAME_TAG) {
        displayName(Component.text("Meta", NamedTextColor.YELLOW))
    }

    this[3].item(Material.NETHER_STAR) {
        displayName(Component.text("Goals", NamedTextColor.GOLD))
    }

    this[5].item(Material.TARGET) {
        Component.text("Targets", NamedTextColor.GREEN)
    }

    this[7].item(Material.GHAST_SPAWN_EGG) {
        Component.text("Type", NamedTextColor.GRAY)
    }

    this[7].onClick { event ->
        val canvas: Canvas = canvas(event.player)
        canvas.render(TypeScreen)
    }
}