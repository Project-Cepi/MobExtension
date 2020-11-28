package world.cepi.mobextension.mob.meta

import net.minestom.server.command.builder.arguments.Argument
import net.minestom.server.entity.Entity
import net.minestom.server.entity.Player

/**
 * Class used for handling MobMeta.
 */
interface MobMeta<T> {

    /**
     * This runs when the mob is created.
     * 
     * @param entity The entity the task is acting upon.
     * 
     * @param executor The player who is editing this mob
     * 
     * @param type The type used, whether its an item, a selector, or more.
     * 
     */
    fun renderTask(entity: Entity, executor: Player, type: T)

    /**
     * Generates an Argument corresponding with the type.
     *
     * @return The argument generated from that type.
     */
    fun generateArgument(): Argument<*>
}