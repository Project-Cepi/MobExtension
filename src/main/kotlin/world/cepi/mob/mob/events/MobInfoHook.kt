package world.cepi.mob.mob.events

import com.mattworzala.canvas.Canvas
import com.mattworzala.canvas.CanvasProvider
import net.minestom.server.event.player.PlayerStartDiggingEvent
import world.cepi.mob.ui.TypeScreen

object MobInfoHook {

    fun hook(event: PlayerStartDiggingEvent) = with(event) {
        val canvas: Canvas = CanvasProvider.canvas(event.player)
        canvas.render(TypeScreen)
    }

}