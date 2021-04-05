package world.cepi.mobextension.commands.subcommands

import world.cepi.mobextension.commands.mobTargetSet
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
    mobTargetSet
)