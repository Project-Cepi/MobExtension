package world.cepi.mobextension.commands.subcommands

import world.cepi.mobextension.commands.mobGoalSet
import world.cepi.mobextension.goal.GoalObjectCollection
import world.cepi.mobextension.goal.SerializableGoal

internal object GoalSubcommand: GenericMobListSubcommand(
    "goal",
    GoalObjectCollection,
    { mob, any -> mob.properties.addGoal(any as SerializableGoal )},
    mobGoalSet
)