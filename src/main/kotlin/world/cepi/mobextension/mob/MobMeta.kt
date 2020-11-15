package world.cepi.mobextension.mob

import net.minestom.server.command.builder.arguments.Argument
import net.minestom.server.entity.Entity
import net.minestom.server.entity.Player

open class MobMeta {
    fun renderTask(entity: Entity, executor: Player, arguments: List<Argument<*>>) {}
}