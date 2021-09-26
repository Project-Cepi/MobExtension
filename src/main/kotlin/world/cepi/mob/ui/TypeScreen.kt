package world.cepi.mob.ui

import com.mattworzala.canvas.Slot
import com.mattworzala.canvas.extra.row
import com.mattworzala.canvas.fragment
import com.mattworzala.canvas.useState
import net.kyori.adventure.sound.Sound
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import net.kyori.adventure.text.format.TextDecoration
import net.minestom.server.item.ItemStack
import net.minestom.server.item.Material
import net.minestom.server.sound.SoundEvent
import world.cepi.kstom.util.component1
import world.cepi.kstom.util.component2
import world.cepi.kstom.util.component3
import world.cepi.mob.mob.EntityEggData
import world.cepi.mob.mob.mobEgg

private const val PAGE_SIZE = 9 * 4

fun TypeScreen() = fragment(9, 6) {

    val menuItems = EntityEggData.values().map {
        { slot: Slot ->
            slot.item(it.material) {
                displayName(
                    Component.text(it.displayName, NamedTextColor.YELLOW)
                        .decoration(TextDecoration.ITALIC, false)
                )
            }

            slot.onClick { event ->
                val mob = event.player.mobEgg!!

                mob.type = it.type

                val (x, y, z) = event.player.position

                event.player.playSound(
                    Sound.sound(SoundEvent.BLOCK_NOTE_BLOCK_PLING, Sound.Source.MASTER, 1f, 2f),
                    x, y, z
                )

                event.player.itemInMainHand = mob.generateEgg(event.player.itemInMainHand)
            }
        }
    }

    var page by useState(0);

    val items = menuItems
        .drop(page * PAGE_SIZE)
        .take(PAGE_SIZE)

    this.container.title = Component.text("Choose a Type")

    row(0, 5) {
        item = ItemStack.of(Material.WHITE_STAINED_GLASS_PANE).withDisplayName(Component.empty())
    }

    slot(0, 5) {
        item = ItemStack.of(Material.RED_STAINED_GLASS_PANE)
            .withDisplayName(Component.text("Previous Page", NamedTextColor.RED))

        onClick {
            page = (page - 1).coerceAtLeast(0)
        }
    }

    slot(8, 5) {
        item = ItemStack.of(Material.LIME_STAINED_GLASS_PANE)
            .withDisplayName(Component.text("Next Page", NamedTextColor.GREEN))

        onClick {
            page = (page + 1).coerceAtLeast(0)
        }
    }

    items.forEachIndexed { index, slot ->

        slot(this[index + 9])
    }


}