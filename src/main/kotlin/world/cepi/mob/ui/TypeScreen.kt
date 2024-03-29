package world.cepi.mob.ui

import com.mattworzala.canvas.Slot
import com.mattworzala.canvas.extra.row
import com.mattworzala.canvas.fragment
import com.mattworzala.canvas.useState
import net.kyori.adventure.sound.Sound
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import net.kyori.adventure.text.format.TextDecoration
import net.minestom.server.entity.Player
import net.minestom.server.item.Enchantment
import net.minestom.server.item.ItemHideFlag
import net.minestom.server.item.ItemStack
import net.minestom.server.item.Material
import net.minestom.server.sound.SoundEvent
import world.cepi.kstom.item.item
import world.cepi.kstom.util.component1
import world.cepi.kstom.util.component2
import world.cepi.kstom.util.component3
import world.cepi.mob.mob.EntityEggData
import world.cepi.mob.mob.mobEgg

private const val PAGE_SIZE = 9 * 4

fun TypeScreen(player: Player) = fragment(9, 6) {

    var selectedEntityType by useState(player.mobEgg?.type)

    val menuItems = EntityEggData.values().map {
        { slot: Slot ->
            slot.item = item(it.material) {
                displayName(
                    Component.text(it.displayName, NamedTextColor.YELLOW)
                        .decoration(TextDecoration.ITALIC, false)
                )

                if (selectedEntityType == it.type) {
                    enchantment(Enchantment.UNBREAKING, 1)
                    hideFlag(ItemHideFlag.HIDE_ENCHANTS)
                }
            }

            slot.onClick { event ->
                val (x, y, z) = event.player.position

                event.player.playSound(
                    Sound.sound(SoundEvent.BLOCK_NOTE_BLOCK_PLING, Sound.Source.MASTER, 1f, 2f),
                    x, y, z
                )

                selectedEntityType = it.type
                event.player.itemInMainHand = event.player.mobEgg!!.copy(type = it.type).generateEgg(event.player.itemInMainHand)
            }
        }
    }

    var page by useState(0);

    val items = menuItems
        .chunked(PAGE_SIZE)[page]

    this.container.title = Component.text("Choose a Type")

    row(0, 5) {
        item = ItemStack.of(Material.WHITE_STAINED_GLASS_PANE).withDisplayName(Component.empty())
    }

    slot(0, 5) {
        if (page != 0) {
            item = ItemStack.of(Material.RED_STAINED_GLASS_PANE)
                .withDisplayName(
                    Component.text("Previous Page", NamedTextColor.RED)
                        .decoration(TextDecoration.ITALIC, false)
                )

            onClick { event ->
                page = (page - 1).coerceAtLeast(0)

                event.player.playSound(
                    Sound.sound(
                        SoundEvent.ITEM_BOOK_PAGE_TURN,
                        Sound.Source.MASTER,
                        1f,
                        1f
                    )
                )
            }
        } else {
            item = ItemStack.of(Material.GRAY_STAINED_GLASS_PANE)
                .withDisplayName(
                    Component.text("Previous Page (Disabled)", NamedTextColor.GRAY)
                        .decoration(TextDecoration.ITALIC, false)
                )
        }
    }

    slot(8, 5) {
        if (items.size >= PAGE_SIZE) {
            item = ItemStack.of(Material.LIME_STAINED_GLASS_PANE)
                .withDisplayName(
                    Component.text("Next Page", NamedTextColor.GREEN)
                        .decoration(TextDecoration.ITALIC, false)
                )

            onClick { event ->
                page = (page + 1).coerceAtLeast(0)

                event.player.playSound(
                    Sound.sound(
                        SoundEvent.ITEM_BOOK_PAGE_TURN,
                        Sound.Source.MASTER,
                        1f,
                        1f
                    )
                )
            }
        } else {
            item = ItemStack.of(Material.GRAY_STAINED_GLASS_PANE)
                .withDisplayName(
                    Component.text("Next Page (Disabled)", NamedTextColor.GRAY)
                        .decoration(TextDecoration.ITALIC, false)
                )
        }
    }

    items.forEachIndexed { index, slot ->

        slot(this[index + 9])
    }


}