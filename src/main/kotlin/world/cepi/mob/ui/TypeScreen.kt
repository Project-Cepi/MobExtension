package world.cepi.mob.ui

import com.mattworzala.canvas.Slot
import com.mattworzala.canvas.fragment
import net.kyori.adventure.sound.Sound
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import net.kyori.adventure.text.format.TextDecoration
import net.minestom.server.sound.SoundEvent
import world.cepi.kstom.util.component1
import world.cepi.kstom.util.component2
import world.cepi.kstom.util.component3
import world.cepi.mob.mob.EntityEggData
import world.cepi.mob.mob.mobEgg

fun TypeScreen() = fragment(9, 6) {

    this.container.title = Component.text("Choose a Type")

    EntityEggData.values().map {
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
                    Sound.sound(SoundEvent.NOTE_BLOCK_PLING, Sound.Source.MASTER, 1f, 2f),
                    x, y, z
                )

                event.player.itemInMainHand = mob.generateEgg(event.player.itemInMainHand)
            }
        }
    }.forEachIndexed { index, slot ->

        if (index > (9 * 6) - 1) return@forEachIndexed

        slot(this[index])
    }


}