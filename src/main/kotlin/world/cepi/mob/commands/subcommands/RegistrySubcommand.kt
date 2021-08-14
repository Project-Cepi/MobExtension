package world.cepi.mob.commands.subcommands

import net.kyori.adventure.text.Component
import net.minestom.server.command.builder.Command
import net.minestom.server.command.builder.arguments.ArgumentType
import net.minestom.server.command.builder.exception.ArgumentSyntaxException
import net.minestom.server.entity.Player
import world.cepi.kepi.data.ContentDataHandler
import world.cepi.kepi.messages.sendFormattedMessage
import world.cepi.kstom.command.addSyntax
import world.cepi.kstom.command.arguments.literal
import world.cepi.kstom.command.arguments.suggest
import world.cepi.mob.commands.MobCommand
import world.cepi.mob.commands.properFileName
import world.cepi.mob.data.MobModel
import world.cepi.mob.data.RegisteredMob
import world.cepi.mob.mob.mobEgg
import java.util.function.Supplier

internal object RegistrySubcommand : Command("registry") {

    init {

        val spawn = "spawn".literal()
        val add = "add".literal()
        val get = "get".literal()

        val newMob = ArgumentType.Word("newName").map { value ->
            if (ContentDataHandler.main.getAll(MobModel).any { it.first.mobKey == value })
                throw ArgumentSyntaxException("Mob already exists", value, 1)

            value
        }

        val amount = ArgumentType.Integer("amount").max(100).min(1)
        amount.defaultValue = Supplier { 1 }

        val registeredMob = ArgumentType.Word("mobs").map { value ->
            ContentDataHandler.main.getAll(MobModel).firstOrNull { it.first.mobKey == value }?.first
                ?: throw ArgumentSyntaxException("Invalid mob", value, 1)
        }.suggest {
            ContentDataHandler.main.getAll(MobModel).map { it.first.mobKey }
        }

        MobCommand.setArgumentCallback({ commandSender, _ ->
            commandSender.sendFormattedMessage(Component.text(properFileName))
        }, registeredMob)

        addSyntax(spawn, registeredMob, amount) {

            if (sender !is Player) return@addSyntax

            val player = sender as Player

            val mob = context[registeredMob].mob

            repeat(context.get(amount)) {
                val creature = mob.generateMob() ?: return@addSyntax
                creature.setInstance(player.instance!!, player.position)
            }
        }

        addSyntax(get, registeredMob) {

            if (sender !is Player) return@addSyntax

            (sender as Player).inventory.addItemStack(context[registeredMob].mob.generateEgg())
        }

        addSyntax(add, newMob) {
            if (sender !is Player) return@addSyntax

            val egg = (sender as? Player)?.mobEgg ?: return@addSyntax

            ContentDataHandler.main[MobModel] = RegisteredMob(context[newMob], egg)
        }
    }

}