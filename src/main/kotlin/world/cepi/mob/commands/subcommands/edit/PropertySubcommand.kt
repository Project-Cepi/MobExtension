package world.cepi.mob.commands.subcommands.edit

import world.cepi.kepi.Kepi
import world.cepi.kepi.command.subcommand.KepiMetaSubcommand
import world.cepi.kstom.command.arguments.literal
import world.cepi.mob.mob.mobEgg
import world.cepi.mob.property.MobProperty
import world.cepi.mob.util.MobUtils

object PropertySubcommand : KepiMetaSubcommand<MobProperty>(
    MobProperty::class,
    { _, name ->
        name.literal()
    },
    "property",
    "property",
    addLambda@ { instance, name ->
        if (!MobUtils.hasMobEgg(sender)) return@addLambda

        val mob = player.mobEgg ?: return@addLambda

        player.itemInMainHand = mob.add(instance).generateEgg(player.itemInMainHand)

        player.playSound(Kepi.editItemSound)
    },
    removelambda@ { clazz, name ->
        if (!MobUtils.hasMobEgg(sender)) return@removelambda

        val mob = player.mobEgg ?: return@removelambda

        player.itemInMainHand = mob
            .remove(clazz)
            .generateEgg(player.itemInMainHand)

        player.playSound(Kepi.editItemSound)
    }
)