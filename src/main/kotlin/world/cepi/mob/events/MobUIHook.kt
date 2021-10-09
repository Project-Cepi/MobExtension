package world.cepi.mob.events

import com.mattworzala.canvas.ext.canvas
import net.minestom.server.entity.Player
import net.minestom.server.event.player.PlayerHandAnimationEvent
import net.minestom.server.event.player.PlayerStartDiggingEvent
import net.minestom.server.event.trait.PlayerEvent
import world.cepi.kstom.raycast.HitType
import world.cepi.kstom.raycast.RayCast
import world.cepi.mob.mob.mobEgg
import world.cepi.mob.ui.MainScreen

internal object MobUIHook {

    private fun hook(event: PlayerEvent) = with(event) {
        // Make sure player has mob egg
        player.mobEgg ?: return

        event.player.canvas.render(::MainScreen)
    }

    fun hookDig(event: PlayerStartDiggingEvent) = hook(event)

    fun hookAnimation(event: PlayerHandAnimationEvent) = with(event) {
        if (hand == Player.Hand.MAIN
            && player.openInventory == null
            && player.mobEgg != null
            && RayCast.castRay(
                player.instance!!,
                player,
                player.position.asVec().add(.0, player.eyeHeight, .0),
                player.position.direction(),
                5.0
        ).hitType == HitType.NONE)
            hook(this)

    }

}