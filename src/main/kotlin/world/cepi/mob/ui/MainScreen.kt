package world.cepi.mob.ui

import com.mattworzala.canvas.Canvas
import com.mattworzala.canvas.CanvasProvider.canvas
import com.mattworzala.canvas.fragment
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import net.kyori.adventure.text.format.TextDecoration
import net.minestom.server.item.Material


val MainScreen = fragment(9, 1) {

    this[1].item(Material.NAME_TAG) {
        displayName(Component.text("Meta", NamedTextColor.YELLOW).decoration(TextDecoration.ITALIC, false))
    }

    this[3].item(Material.NETHER_STAR) {
        displayName(Component.text("Goals", NamedTextColor.GOLD).decoration(TextDecoration.ITALIC, false))
    }

    this[5].item(Material.TARGET) {
        displayName(Component.text("Targets", NamedTextColor.GREEN).decoration(TextDecoration.ITALIC, false))
    }

    this[7].item(Material.GHAST_SPAWN_EGG) {
        displayName(Component.text("Type", NamedTextColor.GRAY).decoration(TextDecoration.ITALIC, false))
    }

    this[7].onClick { event ->
        val canvas: Canvas = canvas(event.player)
        canvas.render(TypeScreen)
    }
}