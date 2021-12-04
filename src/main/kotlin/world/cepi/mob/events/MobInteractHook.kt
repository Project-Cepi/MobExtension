package world.cepi.mob.events

import net.minestom.server.MinecraftServer
import net.minestom.server.event.player.PlayerEntityInteractEvent

object MobInteractHook {

    fun hook(event: PlayerEntityInteractEvent) = with(event) {
        MinecraftServer.getGlobalEventHandler().call(EntityInteractEvent(entity, player))
    }

}