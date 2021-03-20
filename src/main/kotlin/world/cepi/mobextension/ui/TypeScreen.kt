package world.cepi.mobextension.ui

import com.mattworzala.canvas.Props
import com.mattworzala.canvas.component
import net.minestom.server.sound.Sound
import net.minestom.server.sound.SoundCategory
import world.cepi.mobextension.EntityData
import world.cepi.mobextension.mob

val TypeScreen = component<Props>(9, 6) {

    list(0) {
        slots.addAll(EntityData.mobTypeList.map {
            {
                item {
                    material = it.material

                    displayName = "<reset><yellow>${it.displayName}".asMini()
                }

                onClick { event ->
                    val mob = event.player.mob!!

                    mob.properties.setType(it.type)

                    event.player.playSound(Sound.BLOCK_NOTE_BLOCK_PLING, SoundCategory.MASTER, 1f, 1f)

                    event.player.itemInMainHand = mob.generateEgg()
                }
            }
        })
    }

}