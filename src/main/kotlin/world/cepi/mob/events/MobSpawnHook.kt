package world.cepi.mob.events

import net.minestom.server.event.player.PlayerUseItemEvent
import net.minestom.server.event.player.PlayerUseItemOnBlockEvent
import world.cepi.kstom.raycast.RayCast
import world.cepi.mob.mob.mobEgg

object MobSpawnHook {

    fun hook(event: PlayerUseItemOnBlockEvent) = with(event) {
        val mob = player.mobEgg ?: return

        val creature = mob.generateMob() ?: return

        creature.setInstance(
            player.instance!!,
            // don't spawn the entity in the block
            event.position.toPosition().clone().add(.5, 1.0, .5).apply {
                yaw = player.position.yaw
            }
        )
    }

    fun hookInteract(event: PlayerUseItemEvent) = with(event) {
        val mob = player.mobEgg ?: return

        val creature = mob.generateMob() ?: return

        val originalPosition = player.position.clone().add(.0, player.eyeHeight, .0).toVector()

        val raycast = RayCast.castRay(
            player.instance!!,
            player,
            originalPosition.clone(),
            player.position.direction,
            100.0,
            0.25
        )

        if (raycast.finalPosition.distance(originalPosition) < 5)
            return@with

        creature.setInstance(
            player.instance!!,
            // don't spawn the entity in the block
            raycast.finalPosition.toPosition()
        )
    }

}