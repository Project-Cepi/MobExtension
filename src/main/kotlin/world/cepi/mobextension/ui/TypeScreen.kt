package world.cepi.mobextension.ui

import com.mattworzala.canvas.Slot
import com.mattworzala.canvas.fragment
import net.kyori.adventure.sound.Sound
import net.minestom.server.sound.SoundEvent
import world.cepi.kstom.adventure.asMini
import world.cepi.mobextension.EntityData
import world.cepi.mobextension.mob

val TypeScreen = fragment(9, 6) {

    EntityData.mobTypeList.map {
        { slot: Slot ->
            slot.item(it.material) {
                displayName("<reset><yellow>${it.displayName}".asMini())
            }

            slot.onClick { event ->
                val mob = event.player.mob!!

                mob.properties.setType(it.type)

                event.player.playSound(
                    Sound.sound(SoundEvent.BLOCK_NOTE_BLOCK_PLING, Sound.Source.MASTER, 1f, 1f)
                )

                event.player.itemInMainHand = mob.generateEgg()
            }
        }
    }.forEachIndexed { index, slot ->

        if (index > (9 * 6) - 1) return@forEachIndexed

        slot(this[index])
    }


}