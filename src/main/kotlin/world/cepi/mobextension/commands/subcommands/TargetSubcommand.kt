package world.cepi.mobextension.commands.subcommands

import world.cepi.mobextension.commands.mobTargetSet
import world.cepi.mobextension.targets.SerializableTarget
import world.cepi.mobextension.targets.TargetObjectCollection

internal object TargetSubcommand: GenericMobListSubcommand(
    "target",
    TargetObjectCollection,
    { mob, any -> mob.properties.addTarget(any as SerializableTarget )},
    mobTargetSet
)