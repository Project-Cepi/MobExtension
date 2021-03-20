package world.cepi.mobextension.ui

import com.mattworzala.canvas.*
import net.kyori.adventure.platform.minestom.MinestomComponentSerializer
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.minimessage.MiniMessage
import net.minestom.server.item.Material
import com.mattworzala.canvas.CanvasProvider.canvas


val MainScreen = component<Props>(9, 1) {

    get(1).item {
        material = Material.NAME_TAG
        displayName = "<yellow>Meta".asMini()
    }

    get(3).item {
        material = Material.NETHER_STAR
        displayName = "<gold>Goals".asMini()
    }

    get(5).item {
        material = Material.TARGET
        displayName = "<green>Targets".asMini()
    }

    slot(7) {
        item {
            material = Material.GHAST_SPAWN_EGG
            displayName = "<gray>Type".asMini()
        }

        onClick { event ->
            val canvas: Canvas = canvas(event.player)
            canvas.render(TypeScreen, BlankProps);
        }

    }
}

inline fun String.asMini() = MiniMessage.get().parse(this)