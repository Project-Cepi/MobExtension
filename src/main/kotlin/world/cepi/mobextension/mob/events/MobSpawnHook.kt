package world.cepi.mobextension.mob.events

import net.minestom.server.event.player.PlayerUseItemOnBlockEvent
import world.cepi.mobextension.mob.mobEgg

object MobSpawnHook {

    fun hook(event: PlayerUseItemOnBlockEvent) = with(event) {
        val mob = player.mobEgg ?: return

        val creature = mob.generateMob() ?: return

        creature.setInstance(
            player.instance!!,
            // don't spawn the entity in the block
            event.position.toPosition().clone().add(.0, 1.0, .0)
        )
    }

}