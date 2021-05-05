package world.cepi.mobextension.commands

import net.minestom.server.command.CommandSender
import net.minestom.server.command.builder.Command
import net.minestom.server.command.builder.arguments.ArgumentType
import net.minestom.server.entity.EntityType
import net.minestom.server.entity.Player
import net.minestom.server.item.Material
import world.cepi.kepi.messages.sendFormattedTranslatableMessage
import world.cepi.kstom.command.addSyntax
import world.cepi.kstom.command.arguments.literal
import world.cepi.kstom.item.get
import world.cepi.mobextension.*
import world.cepi.mobextension.MobExtension.Companion.dataDir
import world.cepi.mobextension.commands.subcommands.*
import java.io.File
import java.util.function.Supplier

object MobCommand : Command("mob") {

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

//        addSyntax(ui) { sender ->
//            if (!hasMobEgg(sender)) return@addSyntax
//
//            val player = sender as Player
//
//            val canvas: Canvas = CanvasProvider.canvas(player)
//            canvas.render(MainScreen)
//        }

        addSyntax(create) { sender ->

            val player = sender as Player

            if (
                // Player has an item
                !player.itemInMainHand.isAir
                // That item is not registered in list of types
                && !EntityData.mobTypeList.map { it.material }.contains(player.itemInMainHand.material)
            ) {
                player.sendFormattedTranslatableMessage("mob", "egg.required")
                return@addSyntax
            }

            val mob = Mob(Mob.Properties().setType(player.itemInMainHand.entityData?.type ?: EntityType.ARMOR_STAND))

            player.itemInMainHand = mob.generateEgg()

            player.sendFormattedTranslatableMessage("mob", "create")

        }

        addSyntax(spawn, amount) { sender, context ->
            if (!hasMobEgg(sender)) return@addSyntax

            val player = sender as Player

            val mob = player.mob ?: return@addSyntax

            repeat(context.get(amount)) {
                val creature = mob.generateMob() ?: return@addSyntax
                creature.setInstance(player.instance!!, player.position)
            }
        }

        addSubcommand(SpawnerSubcommand)
        addSubcommand(InfoSubcommand)
        addSubcommand(ButcherSubcommand)
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
            sender.sendFormattedTranslatableMessage("item", "main.required")
            return false
        }

        if (sender.itemInMainHand.meta.get<Mob>(Mob.mobKey, module) == null) {
            sender.sendFormattedTranslatableMessage("mob", "egg.created.required")
            return false
        }

        return true
    }

}