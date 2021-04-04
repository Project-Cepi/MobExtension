package world.cepi.mobextension.commands

import com.mattworzala.canvas.BlankProps
import com.mattworzala.canvas.Canvas
import com.mattworzala.canvas.CanvasProvider
import net.kyori.adventure.text.Component
import net.minestom.server.command.CommandSender
import net.minestom.server.command.builder.Command
import net.minestom.server.command.builder.arguments.ArgumentType
import net.minestom.server.entity.Player
import net.minestom.server.item.Material
import world.cepi.kepi.messages.sendFormattedMessage
import world.cepi.kstom.command.addSyntax
import world.cepi.kstom.command.arguments.asSubcommand
import world.cepi.mobextension.Mob
import world.cepi.mobextension.MobExtension.Companion.dataDir
import world.cepi.mobextension.commands.subcommands.*
import world.cepi.mobextension.commands.subcommands.InfoSubcommand
import world.cepi.mobextension.commands.subcommands.MetaSubcommand
import world.cepi.mobextension.commands.subcommands.RegistrySubcommand
import world.cepi.mobextension.commands.subcommands.SpawnerSubcommand
import world.cepi.mobextension.commands.subcommands.TypeSubcommand
import world.cepi.mobextension.entityData
import world.cepi.mobextension.ui.MainScreen
import java.io.File

object MobCommand : Command("mob") {

    internal var files: List<File> = listOf()

    internal fun refreshFiles(): List<File> {
        return dataDir.walk().filter { it.isFile }.toList()
    }

    init {

        dataDir.mkdirs()

        files = refreshFiles()

        val ui = "ui".asSubcommand()

        val create = "create".asSubcommand()

        val spawn = "spawn".asSubcommand()

        val amount = ArgumentType.Integer("amount").max(100).min(1)
        amount.defaultValue = 1

        addSyntax(ui) { sender ->
            if (!hasMobEgg(sender)) return@addSyntax

            val player = sender as Player

            val canvas: Canvas = CanvasProvider.canvas(player)
            canvas.render(MainScreen, BlankProps);
        }

        addSyntax(create) { sender ->

            if (!hasMobEgg(sender)) return@addSyntax

            val player = sender as Player

            val mob = Mob(Mob.Properties().setType(player.itemInMainHand.entityData!!.type))

            player.itemInMainHand = mob.generateEgg()

            player.sendFormattedMessage(Component.text(mobCreated))

        }

        addSyntax(spawn, amount) { sender, context ->
            if (!hasMobEgg(sender)) return@addSyntax

            val player = sender as Player

            val mob = player.itemInMainHand.data?.get<Mob>(Mob.mobKey)!!

            repeat(context.get(amount)) {
                val creature = mob.generateMob() ?: return@addSyntax
                creature.setInstance(player.instance!!, player.position)
            }
        }

        addSubcommand(SpawnerSubcommand)
        addSubcommand(InfoSubcommand)
        addSubcommand(TypeSubcommand)
        addSubcommand(RegistrySubcommand)

        addSubcommand(MetaSubcommand)
        addSubcommand(GoalSubcommand)
        addSubcommand(TargetSubcommand)
    }

    override fun onDynamicWrite(sender: CommandSender, text: String): Array<out String> {
        return files.map { it.nameWithoutExtension }.toTypedArray()
    }

    /**
     * Checks if the sender has a mob egg.
     *
     * @param sender The sender to check
     *
     * @return If the sender has the egg or not (false if they don't)
     */
    fun hasMobEgg(sender: CommandSender): Boolean {
        if (sender !is Player) return false

        if (sender.itemInMainHand.material == Material.AIR) {
            sender.sendFormattedMessage(Component.text(mustHaveItemInHand))
            return false
        }

        if (sender.itemInMainHand.data?.get<Mob>(Mob.mobKey) == null) {
            sender.sendFormattedMessage(Component.text(mobSpawnEggInHand))
            return false
        }

        return true
    }

}