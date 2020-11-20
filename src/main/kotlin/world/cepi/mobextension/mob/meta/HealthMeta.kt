package world.cepi.mobextension.mob.meta

import net.minestom.server.command.builder.arguments.Argument
import net.minestom.server.command.builder.arguments.ArgumentType
import net.minestom.server.entity.Entity
import net.minestom.server.entity.LivingEntity
import net.minestom.server.entity.Player
import world.cepi.mobextension.mob.MobMeta

object HealthMeta : MobMeta<Float> {
    override fun renderTask(entity: Entity, executor: Player, type: Float) {
        if (entity is LivingEntity) {
            val livingEntity = entity as LivingEntity
            entity.health = type
        }
    }

    override fun generateArgument(): Argument<*> {
        return ArgumentType.Float("health")
    }


}