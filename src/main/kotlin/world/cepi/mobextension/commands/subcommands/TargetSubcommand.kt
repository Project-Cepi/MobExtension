package world.cepi.mobextension.commands.subcommands

import world.cepi.kepi.messages.translations.formatTranslableMessage
import world.cepi.mobextension.targets.SerializableTarget
import world.cepi.mobextension.targets.TargetObjectCollection

internal object TargetSubcommand: GenericMobListSubcommand(
    name = "target",
    collection = TargetObjectCollection,
    addToMob = { mob, any -> mob.properties.addTarget(any as SerializableTarget )},
    grabFromMob = { mob -> mob.properties.targets },
    displayName = "Goals",
    unknownName = "Unknown Goal",
    drop = "Goal",
    { sender -> sender.formatTranslableMessage("mob", "target.add") }
)