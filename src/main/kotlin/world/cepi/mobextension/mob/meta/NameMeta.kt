package world.cepi.mobextension.mob.meta

import net.minestom.server.chat.ColoredText
import net.minestom.server.entity.Entity
import net.minestom.server.entity.Player
import net.minestom.server.command.builder.arguments.*
import world.cepi.mobextension.mob.meta.MobMeta

object NameMeta : MobMeta<String> {
    override fun renderTask(entity: Entity, executor: Player, type: String) {
        entity.customName = ColoredText.of(type)
    }

    override fun generateArgument(): Argument<*> {
        return ArgumentType.String("name")
    }
}