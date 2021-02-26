package world.cepi.mobextension.spawner

import net.minestom.server.MinecraftServer
import net.minestom.server.event.entity.EntityDeathEvent
import net.minestom.server.instance.Instance
import net.minestom.server.timer.Task
import net.minestom.server.utils.BlockPosition
import net.minestom.server.utils.time.TimeUnit
import world.cepi.kstom.addEventCallback
import world.cepi.mobextension.Mob
import java.util.concurrent.atomic.AtomicInteger

/** Represents a mob spawning, containing where to spawn, and the mob to spawn + the time/limit. */
class MobSpawner(
    /** What instance this mob spawner should be located in. */
    val instance: Instance,
    /** All viable locations this mob can spawn in. Should be one block above the ground */
    val viablePositions: List<BlockPosition>,
    /** The mob to spawn */
    val mob: Mob,
    /** How many ticks it should take for the next mob to spawn. */
    val ticksPerSpawn: Int = 50,
    /** How many mobs can be controlled by this spawner at once. */
    val limit: Int = 100
) {

    /** Internal scheduler that can be modified and cancelled. */
    private var schedule: Task? = null

    /** The current amount of mobs linked to this spawner. Used to accurately calculate the [limit] of this spawner. */
    private val amount: AtomicInteger = AtomicInteger()

    /** Updates the scheduler of this spawner. */
    fun update() {
        schedule?.cancel()

        schedule = MinecraftServer.getSchedulerManager().buildTask {

            if (amount.get() >= limit) return@buildTask

            val position = viablePositions.random().toPosition()

            val creature = mob.generateMob()!!

            amount.getAndIncrement()

            creature.setInstance(instance, position)

            creature.addEventCallback<EntityDeathEvent> {
                amount.decrementAndGet()
            }

        }.repeat(ticksPerSpawn.toLong(), TimeUnit.TICK).schedule()
    }

    companion object {

        private val spawners: MutableMap<String, MobSpawner> = mutableMapOf()

        /**
         * Creates a spawner with a name linked to it.
         *
         * @param name The name (must be unique) of the spawner
         * @param spawner The spanwer to link to said [name]
         */
        fun createSpawner(name: String, spawner: MobSpawner) {
            spawners[name] = spawner
        }

        /**
         * Gets a spawner from its name
         *
         * @param name The name (id) of this spawner
         *
         * @return A spawner linked to said [name] paramater.
         */
        fun getSpawner(name: String): MobSpawner? = spawners[name]

        /**
         * Removes a spawner from the map based on its name
         *
         * @param name The name of the spawner to remove.
         */
        fun removeSpawner(name: String) {
            spawners.remove(name)
        }

    }
}