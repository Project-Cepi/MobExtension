package world.cepi.mobextension.ui

import com.mattworzala.canvas.*
import net.kyori.adventure.text.minimessage.MiniMessage
import net.minestom.server.item.Material
import com.mattworzala.canvas.CanvasProvider.canvas
import world.cepi.kstom.adventure.asMini


//val MainScreen = fragment(9, 1) {
//
//    this[1].item {
//        material = Material.NAME_TAG
//        displayName = "<yellow>Meta".asMini()
//    }
//
//    this[3].item {
//        material = Material.NETHER_STAR
//        displayName = "<gold>Goals".asMini()
//    }
//
//    this[5].item {
//        material = Material.TARGET
//        displayName = "<green>Targets".asMini()
//    }
//
//    this[7].item {
//        material = Material.GHAST_SPAWN_EGG
//        displayName = "<gray>Type".asMini()
//    }
//
//    this[7].onClick { event ->
//        val canvas: Canvas = canvas(event.player)
//        canvas.render(TypeScreen)
//    }
//}