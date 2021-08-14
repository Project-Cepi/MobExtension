package world.cepi.mob.commands

import com.mattworzala.canvas.CanvasProvider
import net.minestom.server.command.builder.Command
import net.minestom.server.command.builder.arguments.ArgumentType
import net.minestom.server.entity.EntityType
import net.minestom.server.entity.Player
import world.cepi.kepi.command.subcommand.applyHelp
import world.cepi.kepi.messages.sendFormattedTranslatableMessage
import world.cepi.kstom.command.addSubcommands
import world.cepi.kstom.command.addSyntax
import world.cepi.kstom.command.arguments.defaultValue
import world.cepi.kstom.command.arguments.literal
import world.cepi.mob.*
import world.cepi.mob.MobExtension.Companion.dataDir
import world.cepi.mob.commands.subcommands.*
import world.cepi.mob.commands.subcommands.edit.GoalSubcommand
import world.cepi.mob.commands.subcommands.edit.MetaSubcommand
import world.cepi.mob.commands.subcommands.edit.TargetSubcommand
import world.cepi.mob.commands.subcommands.edit.TypeSubcommand
import world.cepi.mob.mob.EntityEggData
import world.cepi.mob.mob.Mob
import world.cepi.mob.mob.entityEggData
import world.cepi.mob.mob.mobEgg
import world.cepi.mob.ui.MainScreen
import world.cepi.mob.util.MobUtils.hasMobEgg
import java.io.File
import java.util.function.Supplier

internal object MobCommand : Command("mob") {

    init {

        dataDir.mkdirs()

        val ui = "ui".literal()

        val create = "create".literal()

        val spawn = "spawn".literal()

        val amount = ArgumentType.Integer("amount").max(100).min(1)
        amount.defaultValue = Supplier { 1 }

        addSyntax(ui) {
            if (!hasMobEgg(sender)) return@addSyntax

            val player = sender as Player

            val canvas = CanvasProvider.canvas(player)
            canvas.render { MainScreen() }
        }

        val argumentType = ArgumentType.Word("type")
            .from(*EntityEggData.values().map { it.type.name.lowercase() }.toTypedArray())
            .map { input -> EntityEggData.values()
                .firstOrNull { it.type.name.equals(input, ignoreCase = true) } }
            .defaultValue(EntityEggData.ZOMBIE)

        addSyntax(create, argumentType) {

            val player = sender as Player

            if (
                // Player has an item
                !player.itemInMainHand.isAir
                // That item is not registered in list of types
                && !EntityEggData.values().map { it.material }.contains(player.itemInMainHand.material)
            ) {
                player.sendFormattedTranslatableMessage("mob", "egg.required")
                return@addSyntax
            }

            val mob = Mob().apply {
                type = context[argumentType]
                    ?.type ?: player.itemInMainHand.entityEggData?.type ?: EntityType.ZOMBIE
            }

            player.itemInMainHand = mob.generateEgg()

            player.sendFormattedTranslatableMessage("mob", "create")

        }

        addSyntax(spawn, amount) {
            if (!hasMobEgg(sender)) return@addSyntax

            val player = sender as Player

            val mob = player.mobEgg ?: return@addSyntax

            repeat(context.get(amount)) {
                val creature = mob.generateMob() ?: return@addSyntax
                creature.setInstance(player.instance!!, player.position)
            }
        }

        applyHelp {
            """
                Need help with mobs?
                Start by using <yellow>/mob create,
                add stats with <yellow>/mob meta,
                and add behavior with <yellow>/mob goal
                and <yellow>/mob target
            """.trimIndent()
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

}