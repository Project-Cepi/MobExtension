package world.cepi.mob.commands.subcommands.edit

import world.cepi.kepi.messages.translations.formatTranslableMessage
import world.cepi.mob.targets.SerializableTarget

internal object TargetSubcommand: GenericMobListSubcommand(
    name = "target",
    sealedClass = SerializableTarget::class,
    addToMob = { mob, any -> mob.addTarget(any as SerializableTarget )},
    grabFromMob = { mob -> mob.targets },
    displayName = "Targets",
    unknownName = "Unknown Target",
    drop = "Target",
    { sender -> sender.formatTranslableMessage("mob", "target.add") }
)