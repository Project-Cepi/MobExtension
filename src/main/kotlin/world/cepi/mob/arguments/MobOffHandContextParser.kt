package world.cepi.mob.arguments

import net.kyori.adventure.text.Component
import net.minestom.server.command.CommandSender
import net.minestom.server.entity.Player
import world.cepi.kstom.command.arguments.context.ContextParser
import world.cepi.mob.mob.Mob
import world.cepi.mob.mob.mobEggOffHand

object MobOffHandContextParser : ContextParser<Mob> {

    override fun parse(sender: CommandSender): Mob? =
        (sender as? Player)?.mobEggOffHand

    override val callbackMessage = Component.text("No mob found in off hand!")
    
}