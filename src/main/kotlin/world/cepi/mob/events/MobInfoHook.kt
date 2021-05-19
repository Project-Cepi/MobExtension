package world.cepi.mob.events

import com.mattworzala.canvas.Canvas
import com.mattworzala.canvas.CanvasProvider
import net.minestom.server.event.player.PlayerStartDiggingEvent
import world.cepi.mob.mob.mobEgg
import world.cepi.mob.ui.MainScreen

object MobInfoHook {

    fun hook(event: PlayerStartDiggingEvent) = with(event) {

        // Make sur eplayer has mob egg
        player.mobEgg ?: return

        val canvas: Canvas = CanvasProvider.canvas(event.player)
        canvas.render(MainScreen)
    }

}