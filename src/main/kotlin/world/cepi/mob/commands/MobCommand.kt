package world.cepi.mob.commands

import com.mattworzala.canvas.CanvasProvider
import net.minestom.server.command.builder.arguments.ArgumentType
import net.minestom.server.entity.EntityType
import net.minestom.server.entity.Player
import net.minestom.server.event.EventNode
import world.cepi.actions.command.subcommands.EventNodeTargetMap
import world.cepi.actions.command.subcommands.EventSubcommand
import world.cepi.kepi.command.subcommand.applyHelp
import world.cepi.kepi.messages.sendFormattedTranslatableMessage
import world.cepi.kstom.Manager
import world.cepi.kstom.command.arguments.literal
import world.cepi.kstom.command.kommand.Kommand
import world.cepi.mob.MobExtension.Companion.dataDir
import world.cepi.mob.commands.subcommands.*
import world.cepi.mob.commands.subcommands.edit.*
import world.cepi.mob.mob.EntityEggData
import world.cepi.mob.mob.Mob
import world.cepi.mob.mob.entityEggData
import world.cepi.mob.mob.mobEgg
import world.cepi.mob.ui.MainScreen
import world.cepi.mob.util.MobUtils.hasMobEgg
import java.util.function.Supplier

internal object MobCommand : Kommand({

    dataDir.mkdirs()

    val ui by literal

    val create by literal

    val spawn by literal

    val amount = ArgumentType.Integer("amount").max(100).min(1)
    amount.defaultValue = Supplier { 1 }

    syntax(ui).onlyPlayers {
        if (!hasMobEgg(sender)) return@onlyPlayers

        val canvas = CanvasProvider.canvas(player)
        canvas.render { MainScreen() }
    }

    val argumentType = ArgumentType.EntityType("type").also {
        it.defaultValue = Supplier { EntityType.ZOMBIE }
    }

    syntax(create, argumentType).onlyPlayers {

        if (
            // Player has an item
            !player.itemInMainHand.isAir
            // That item is not registered in list of types
            && EntityEggData.findByMaterial(player.itemInMainHand.material) == null
        ) {
            player.sendFormattedTranslatableMessage("mob", "egg.required")
            return@onlyPlayers
        }

        if (player.mobEgg != null) {
            player.sendFormattedTranslatableMessage("mob", "egg.none.required")
            return@onlyPlayers
        }

        val mob = Mob(
            type = context[argumentType] ?: player.itemInMainHand.entityEggData?.type ?: EntityType.ZOMBIE
        )

        player.itemInMainHand = mob.generateEgg()

        player.sendFormattedTranslatableMessage("mob", "create")

    }

    syntax(spawn, amount).onlyPlayers {
        if (!hasMobEgg(sender)) return@onlyPlayers

        val mob = player.mobEgg ?: return@onlyPlayers

        repeat(context.get(amount)) {
            mob.spawnMob(player.instance!!, player.position)
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
        TargetSubcommand,
        TemplateSubcommand,
        PropertySubcommand,

        EventSubcommand(
            eventCondition = { player.itemInOffHand.entityEggData != null },
            eventNodes = listOf(
                { player: Player -> EventNodeTargetMap(EventNode.all("h"), { Manager.connection.getPlayer("a")!! to Manager.connection.getPlayer("a")!!}) } to ""
            )
        )
    )


}, "mob")