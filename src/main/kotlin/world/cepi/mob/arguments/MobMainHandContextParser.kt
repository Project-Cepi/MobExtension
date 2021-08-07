package world.cepi.mob.arguments

import net.minestom.server.command.CommandSender
import net.minestom.server.entity.Player
import world.cepi.kstom.command.arguments.context.ContextParser
import world.cepi.mob.mob.Mob
import world.cepi.mob.mob.mobEgg

object MobMainHandContextParser : ContextParser<Mob> {

    override fun parse(sender: CommandSender): Mob? =
        (sender as? Player)?.mobEgg

}