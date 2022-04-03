package world.cepi.mob.spawner

import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient
import net.minestom.server.MinecraftServer
import net.minestom.server.coordinate.Pos
import net.minestom.server.entity.Entity
import net.minestom.server.event.EventFilter
import net.minestom.server.event.EventNode
import net.minestom.server.event.entity.EntityDeathEvent
import net.minestom.server.instance.Instance
import net.minestom.server.timer.Task
import net.minestom.server.utils.time.TimeUnit
import world.cepi.kstom.event.listenOnly
import world.cepi.kstom.serializer.DurationSerializer
import world.cepi.kstom.serializer.PositionSerializer
import world.cepi.mob.mob.Mob
import java.io.Serial
import java.time.Duration
import java.util.*
import java.util.concurrent.atomic.AtomicInteger

/** Represents a mob spawning, containing where to spawn, and the mob to spawn + the time/limit. */
@Serializable
class MobSpawner(
    /** The ID of the instance */
    val id: String,
    /** What instance this mob spawner should be located in. */
    @Transient
    val instance: Instance = MinecraftServer.getInstanceManager().instances.first(),
    /** All viable locations this mob can spawn in. Should be one block above the ground */
    val viablePositions: MutableList<@Serializable(with = PositionSerializer::class) Pos>,
    /** How close the nearest player should be for mobs to spawn. */
    val playerRadiusRequired: Double = 50.0,
    /** The mob to spawn */
    val mob: Mob,
    /** How many ticks it should take for the next mob to spawn. */
    @Serializable(with = DurationSerializer::class)
    private val _spawnOption: Duration = Duration.of(10L, TimeUnit.SECOND),
    /** How many mobs can be controlled by this spawner at once. */
    var limit: Int = 100
) {

    /** How much time it should take for the next mob to spawn. */
    @Transient
    var spawnOption: Duration = _spawnOption
        set(amount) {
            field = amount
            update()
        }

    /** Internal scheduler that can be modified and cancelled. */
    private var schedule: Task? = null

    /** The current amount of mobs linked to this spawner. Used to accurately calculate the [limit] of this spawner. */
    @Transient
    private val amount: AtomicInteger = AtomicInteger()

    @Transient
    private val entities: MutableList<Entity> = mutableListOf()
    private val node = EventNode.type("mob-spawner-${UUID.randomUUID()}", EventFilter.ENTITY) { _, obj ->
        entities.contains(obj)
    }

    init {
        update()
        allNode.addChild(node)

        node.listenOnly<EntityDeathEvent> {
            amount.decrementAndGet()
        }
    }

    fun cancel() = schedule?.cancel()

    /** Updates the scheduler of this spawner. */
    fun update() {
        schedule?.cancel()

        schedule = MinecraftServer.getSchedulerManager().buildTask {

            // If none of the positions have a player whose distance is less than or equal to the required one
            if (viablePositions.none { pos -> instance.players.any { it.getDistance(pos) <= playerRadiusRequired } })
                return@buildTask

            if (amount.get() >= limit) return@buildTask

            if (viablePositions.size == 0) return@buildTask

            val position = viablePositions.random()

            val creature = mob.generateMob()?.mob ?: return@buildTask

            amount.getAndIncrement()

            creature.setInstance(instance, position)

            allEntities.add(creature)
            entities.add(creature)

        }.repeat(spawnOption).schedule()
    }

    companion object {

        internal var mutableSpawners: MutableMap<String, MobSpawner> = mutableMapOf()
        val spawners: Map<String, MobSpawner> = Collections.unmodifiableMap(mutableSpawners)

        /**
         * Creates a spawner with a name linked to it.
         *
         * @param name The name (must be unique) of the spawner
         * @param spawner The spanwer to link to said [name]
         */
        fun createSpawner(name: String, spawner: MobSpawner) {
            mutableSpawners[name] = spawner
        }

        /**
         * Gets a spawner from its name
         *
         * @param name The name (id) of this spawner
         *
         * @return A spawner linked to said [name] paramater.
         */
        fun getSpawner(name: String): MobSpawner? = mutableSpawners[name]

        /**
         * Removes a spawner from the map based on its name
         *
         * @param name The name of the spawner to remove.
         */
        fun removeSpawner(name: String) {
            mutableSpawners.remove(name)?.cancel()
        }

        fun amount(): Int = mutableSpawners.size

        private val allEntities: MutableList<Entity> = mutableListOf()
        val allNode = EventNode.type("mob-spawner-all", EventFilter.ENTITY) { _, obj ->
            allEntities.contains(obj)
        }


    }
}