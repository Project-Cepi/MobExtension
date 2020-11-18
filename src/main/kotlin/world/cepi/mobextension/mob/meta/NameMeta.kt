package world.cepi.mobextension.mob

import net.minestom.server.entity.Entity
import net.minestom.server.entity.Player
import net.minestom.server.command.builder.arguments.*

class NameMeta : MobMeta<String> {
    override fun renderTask(entity: Entity, executor: Player, type: String) {

    }

    override fun generateArgument(type: String): Argument<*> {
        return ArgumentType.Word("name")
    }
}