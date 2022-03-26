package world.cepi.mob.events

import net.kyori.adventure.sound.Sound
import net.minestom.server.coordinate.Pos
import net.minestom.server.event.player.PlayerUseItemEvent
import net.minestom.server.event.player.PlayerUseItemOnBlockEvent
import net.minestom.server.sound.SoundEvent
import net.minestom.server.tag.Tag
import world.cepi.kstom.raycast.RayCast
import world.cepi.mob.mob.mobEgg

object MobSpawnHook {

    val spawnSound = Sound.sound(SoundEvent.ENTITY_ENDER_DRAGON_FLAP, Sound.Source.MASTER, 1f, 2f)

    fun hook(event: PlayerUseItemOnBlockEvent) = with(event) {
        val mob = player.mobEgg ?: return

        if (player.itemInMainHand.hasTag(Tag.Byte("noSpawn"))) return@with

        mob.spawnMob(
            player.instance!!,
            // don't spawn the entity in the block
            Pos.fromPoint(event.position).add(.5, 1.0, .5).withYaw(player.position.yaw())
        )

        player.playSound(spawnSound)
    }

    fun hookInteract(event: PlayerUseItemEvent) = with(event) {
        val mob = player.mobEgg ?: return

        if (player.itemInMainHand.hasTag(Tag.Byte("noSpawn"))) return@with

        val originalPosition = player.position.add(.0, player.eyeHeight, .0).asVec()

        val raycast = RayCast.castRay(
            player.instance!!,
            player,
            originalPosition,
            player.position.direction(),
            100.0,
            0.25
        )

        if (raycast.finalPosition.distance(originalPosition) < 5)
            return@with

        mob.spawnMob(
            player.instance!!,
            // don't spawn the entity in the block
            raycast.finalPosition
        )

        player.playSound(spawnSound)
    }

}