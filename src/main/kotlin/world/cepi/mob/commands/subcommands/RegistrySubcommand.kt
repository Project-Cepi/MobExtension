package world.cepi.mob.commands.subcommands

import net.kyori.adventure.text.Component
import net.minestom.server.command.builder.arguments.ArgumentType
import net.minestom.server.entity.Player
import world.cepi.kepi.command.subcommand.KepiRegistrySubcommand
import world.cepi.kepi.data.ContentDataHandler
import world.cepi.kepi.messages.sendFormattedMessage
import world.cepi.kstom.command.arguments.literal
import world.cepi.mob.commands.properFileName
import world.cepi.mob.data.MobModel
import world.cepi.mob.data.RegisteredMob
import world.cepi.mob.mob.mobEgg
import java.util.function.Supplier

internal object RegistrySubcommand : KepiRegistrySubcommand<RegisteredMob>(
    ContentDataHandler.main,
    MobModel,
    { (sender as Player).inventory.addItemStack(it.mob.generateEgg()) },
    add@ { RegisteredMob(it, (sender as? Player)?.mobEgg ?: return@add null) }
) {

    init {

        val spawn = "spawn".literal()

        val amount = ArgumentType.Integer("amount").max(100).min(1)
        amount.defaultValue = Supplier { 1 }

        argumentCallback(registeredItem) {
            sender.sendFormattedMessage(Component.text(properFileName))
        }

        syntax(spawn, registeredItem, amount).onlyPlayers {
            val mob = context[registeredItem].mob

            repeat(context.get(amount)) {
                mob.spawnMob(player.instance!!, player.position)
            }
        }
    }

}