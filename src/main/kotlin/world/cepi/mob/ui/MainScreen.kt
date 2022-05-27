package world.cepi.mob.ui

import com.mattworzala.canvas.CanvasProvider
import com.mattworzala.canvas.fragment
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import net.kyori.adventure.text.format.TextDecoration
import net.minestom.server.entity.Player
import net.minestom.server.item.Material
import world.cepi.mob.mob.mobEgg
import world.cepi.kstom.item.item

fun MainScreen(player: Player) = fragment(9, 1) {

    this.container.title = Component.text("Mob UI Editor")

    val mob = player.mobEgg

    this[1].item = item(Material.NAME_TAG) {
        displayName(Component.text("Meta", NamedTextColor.YELLOW).decoration(TextDecoration.ITALIC, false))
    }

    this[3].item = item(Material.NETHER_STAR) {
        displayName(Component.text("Goals", NamedTextColor.GOLD).decoration(TextDecoration.ITALIC, false))
    }

    this[5].item = item(Material.TARGET) {
        displayName(Component.text("Targets", NamedTextColor.GREEN).decoration(TextDecoration.ITALIC, false))
    }

    this[7].let {
        it.item = item(Material.GHAST_SPAWN_EGG) {
            displayName(Component.text("Type", NamedTextColor.GRAY).decoration(TextDecoration.ITALIC, false))
            lore(
                Component.empty(),
                Component.text("${mob?.type?.name()}", NamedTextColor.GRAY).decoration(TextDecoration.ITALIC, false)
            )
        }

        it.onClick { event ->
            val canvas = CanvasProvider.canvas(event.player)
            canvas.render { TypeScreen(player) }
        }
    }
}