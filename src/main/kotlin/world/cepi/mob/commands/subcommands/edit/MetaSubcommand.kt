package world.cepi.mob.commands.subcommands.edit

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import net.minestom.server.entity.Player
import world.cepi.kepi.command.subcommand.KepiMetaSubcommand
import world.cepi.kepi.command.subcommand.applyHelp
import world.cepi.kepi.messages.sendFormattedTranslatableMessage
import world.cepi.mob.meta.MobMeta
import world.cepi.mob.mob.mobEgg
import world.cepi.mob.util.MobUtils

internal object MetaSubcommand : KepiMetaSubcommand<MobMeta>(
    MobMeta::class,
    "meta",
    "meta",
    addLambda@ { instance, _ ->
        if (!MobUtils.hasMobEgg(sender)) return@addLambda

        val player = sender as Player

        val mob = player.mobEgg ?: return@addLambda

        mob.addMeta(instance)

        player.itemInMainHand = mob.generateEgg(player.itemInMainHand)
    },
    removeLambda@ { clazz, name ->
        if (!MobUtils.hasMobEgg(sender)) return@removeLambda

        val player = sender as Player

        val mob = player.mobEgg ?: return@removeLambda

        if (mob.metaMap.values.any { it::class == clazz }) {

            mob.metaMap.remove(clazz)

            player.itemInMainHand = mob.generateEgg(player.itemInMainHand)

            player.sendFormattedTranslatableMessage(
                "mob", "meta.add",
                Component.text(
                    clazz.simpleName!!.lowercase().dropLast(name.length),
                    NamedTextColor.BLUE
                )
            )
        }
    }
) {

    init {

        applyHelp {
            """
                Mob meta allows you to define
                certain <blue>properties<gray> of a mob.
                
                It can be either display or behavior meta.
                
                Display meta can be colors or the size of the mob,
                while behavior meta can be health or knockback.
                
                To set a meta, just do
                <yellow>/mob meta set <meta> <value>
                EX: /mob meta set health 5
                
                To remove a meta, do:
                <yellow>/mob meta remove <meta>
                EX: <yellow>/mob meta remove health
            """.trimIndent()
        }
    }

}