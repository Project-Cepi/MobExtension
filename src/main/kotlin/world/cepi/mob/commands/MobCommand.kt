package world.cepi.mob.commands

import com.mattworzala.canvas.CanvasProvider
import net.minestom.server.command.CommandSender
import net.minestom.server.command.builder.Command
import net.minestom.server.command.builder.arguments.ArgumentType
import net.minestom.server.entity.EntityType
import net.minestom.server.entity.Player
import net.minestom.server.item.Material
import world.cepi.kepi.messages.sendFormattedTranslatableMessage
import world.cepi.kstom.command.addSubcommands
import world.cepi.kstom.command.addSyntax
import world.cepi.kstom.command.arguments.literal
import world.cepi.mob.*
import world.cepi.mob.MobExtension.Companion.dataDir
import world.cepi.mob.commands.subcommands.*
import world.cepi.mob.commands.subcommands.edit.GoalSubcommand
import world.cepi.mob.commands.subcommands.edit.MetaSubcommand
import world.cepi.mob.commands.subcommands.edit.TargetSubcommand
import world.cepi.mob.commands.subcommands.edit.TypeSubcommand
import world.cepi.mob.mob.EntityData
import world.cepi.mob.mob.Mob
import world.cepi.mob.mob.entityData
import world.cepi.mob.mob.mobEgg
import world.cepi.mob.ui.MainScreen
import java.io.File
import java.util.function.Supplier

internal object MobCommand : Command("mob") {

    internal var files: List<File> = listOf()

    internal fun refreshFiles(): List<File> {
        return dataDir.walk().filter { it.isFile }.toList()
    }

    init {

        dataDir.mkdirs()

        files = refreshFiles()

        val ui = "ui".literal()

        val create = "create".literal()

        val spawn = "spawn".literal()

        val amount = ArgumentType.Integer("amount").max(100).min(1)
        amount.defaultValue = Supplier { 1 }

        addSyntax(ui) { sender ->
            if (!hasMobEgg(sender)) return@addSyntax

            val player = sender as Player

            val canvas = CanvasProvider.canvas(player)
            canvas.render { MainScreen() }
        }

        addSyntax(create) { sender ->

            val player = sender as Player

            if (
                // Player has an item
                !player.itemInMainHand.isAir
                // That item is not registered in list of types
                && !EntityData.values().map { it.material }.contains(player.itemInMainHand.material)
            ) {
                player.sendFormattedTranslatableMessage("mob", "egg.required")
                return@addSyntax
            }

            val mob = Mob().apply {
                type = player.itemInMainHand.entityData?.type ?: EntityType.ZOMBIE
            }

            player.itemInMainHand = mob.generateEgg()

            player.sendFormattedTranslatableMessage("mob", "create")

        }

        addSyntax(spawn, amount) { sender, context ->
            if (!hasMobEgg(sender)) return@addSyntax

            val player = sender as Player

            val mob = player.mobEgg ?: return@addSyntax

            repeat(context.get(amount)) {
                val creature = mob.generateMob() ?: return@addSyntax
                creature.setInstance(player.instance!!, player.position)
            }
        }

        addSubcommands(
            SpawnerSubcommand,
            InfoSubcommand,
            ButcherSubcommand,
            TypeSubcommand,
            RegistrySubcommand,

            MetaSubcommand,
            GoalSubcommand,
            TargetSubcommand
        )
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
            sender.sendFormattedTranslatableMessage("item", "main.required")
            return false
        }

        if (sender.mobEgg == null) {
            sender.sendFormattedTranslatableMessage("mob", "egg.created.required")
            return false
        }

        return true
    }

}