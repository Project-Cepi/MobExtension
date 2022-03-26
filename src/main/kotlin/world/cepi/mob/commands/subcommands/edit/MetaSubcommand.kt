package world.cepi.mob.commands.subcommands.edit

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import net.minestom.server.command.builder.arguments.ArgumentGroup
import world.cepi.kepi.Kepi
import world.cepi.kepi.command.subcommand.KepiMetaManualSubcommand
import world.cepi.kepi.command.subcommand.applyHelp
import world.cepi.kepi.messages.sendFormattedTranslatableMessage
import world.cepi.kstom.command.arguments.literal
import world.cepi.mob.meta.*
import world.cepi.mob.mob.mobEgg
import world.cepi.mob.property.*
import world.cepi.mob.util.MobUtils
import kotlin.reflect.KClass

internal object MetaSubcommand : KepiMetaManualSubcommand<MobMeta>(
    (list.map { it.nestedClasses }.flatten()) as Collection<KClass<out MobMeta>>,
    { clazz, name ->
        if (clazz.qualifiedName!!.contains("world.cepi.mob.meta.Meta"))
            ArgumentGroup(
                "mainName$clazz",
                clazz.qualifiedName!!
                    .drop("world.cepi.mob.meta.Meta".length)
                    .dropLast(name.length + 1) // Take extra period
                    .replace("Abstract", "")
                    .literal(),
                name.literal()
            )
        else
            name.literal()
    },
    "meta",
    "",
    addLambda@ { instance, name ->
        if (!MobUtils.hasMobEgg(sender)) return@addLambda

        val mob = player.mobEgg ?: return@addLambda

        mob.add(instance)

        player.itemInMainHand = mob.generateEgg(player.itemInMainHand)

        player.sendFormattedTranslatableMessage(
            "mob", "meta.add",
            Component.text(
                name,
                NamedTextColor.BLUE
            )
        )

        player.playSound(Kepi.editItemSound)
    },
    removeLambda@ { clazz, _ ->
        if (!MobUtils.hasMobEgg(sender)) return@removeLambda

        val mob = player.mobEgg ?: return@removeLambda

        mob.remove(clazz)

        player.itemInMainHand = mob.generateEgg(player.itemInMainHand)

        player.playSound(Kepi.editItemSound)
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