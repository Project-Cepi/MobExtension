package world.cepi.mob.ui

import com.mattworzala.canvas.CanvasProvider
import com.mattworzala.canvas.fragment
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import net.kyori.adventure.text.format.TextDecoration
import net.minestom.server.item.Material


val MainScreen = fragment(9, 1) {

    this.container.title = Component.text("Mob UI Editor")

    this[1].item(Material.NAME_TAG) {
        displayName(Component.text("Meta", NamedTextColor.YELLOW).decoration(TextDecoration.ITALIC, false))
    }

    this[3].item(Material.NETHER_STAR) {
        displayName(Component.text("Goals", NamedTextColor.GOLD).decoration(TextDecoration.ITALIC, false))
    }

    this[5].item(Material.TARGET) {
        displayName(Component.text("Targets", NamedTextColor.GREEN).decoration(TextDecoration.ITALIC, false))
    }

    this[7].apply {
        item(Material.GHAST_SPAWN_EGG) {
            displayName(Component.text("Type", NamedTextColor.GRAY).decoration(TextDecoration.ITALIC, false))
        }

        onClick { event ->
            val canvas = CanvasProvider.canvas(event.player)
            canvas.render(TypeScreen)
        }
    }
}