package world.cepi.mobextension.spawner

import net.minestom.server.MinecraftServer
import net.minestom.server.event.entity.EntityDeathEvent
import net.minestom.server.instance.Instance
import net.minestom.server.timer.Task
import net.minestom.server.utils.BlockPosition
import net.minestom.server.utils.math.IntRange
import net.minestom.server.utils.time.TimeUnit
import world.cepi.kstom.addEventCallback
import world.cepi.mobextension.Mob
import java.util.concurrent.atomic.AtomicInteger

class MobSpawner(
    val instance: Instance,
    val viablePositions: List<BlockPosition>,
    val mob: Mob,
    val ticksPerSpawn: IntRange = IntRange(60, 80),
    val limit: Int = 100
) {

    var schedule: Task? = null
    val amount: AtomicInteger = AtomicInteger()

    fun update() {
        schedule?.cancel()

        schedule = MinecraftServer.getSchedulerManager().buildTask {

            if (amount.get() >= limit) return@buildTask

            val position = viablePositions.random().toPosition()

            val creature = mob.generateMob(position)!!

            amount.getAndIncrement()

            creature.setInstance(instance)
            creature.teleport(position)
            creature.refreshPosition(position)

            creature.addEventCallback<EntityDeathEvent> {
                amount.decrementAndGet()
            }

        }.repeat(((ticksPerSpawn.minimum)..(ticksPerSpawn.maximum)).random().toLong(), TimeUnit.TICK).schedule()
    }
}