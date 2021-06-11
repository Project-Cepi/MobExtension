package world.cepi.mob.events

import com.mattworzala.canvas.Canvas
import com.mattworzala.canvas.CanvasProvider
import net.minestom.server.event.player.PlayerStartDiggingEvent
import net.minestom.server.event.player.PlayerUseItemEvent
import net.minestom.server.event.trait.PlayerEvent
import world.cepi.mob.mob.mobEgg
import world.cepi.mob.ui.MainScreen

object MobInfoHook {

    private fun hook(event: PlayerEvent) = with(event) {
        // Make sure player has mob egg
        player.mobEgg ?: return

        val canvas: Canvas = CanvasProvider.canvas(event.player)
        canvas.render { MainScreen() }
    }

    fun hookDig(event: PlayerStartDiggingEvent) = hook(event)
    fun hookInteract(event: PlayerUseItemEvent) = hook(event)

}