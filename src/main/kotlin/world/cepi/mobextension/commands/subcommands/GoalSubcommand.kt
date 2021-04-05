package world.cepi.mobextension.commands.subcommands

import world.cepi.mobextension.commands.mobGoalSet
import world.cepi.mobextension.goal.GoalObjectCollection
import world.cepi.mobextension.goal.SerializableGoal

internal object GoalSubcommand: GenericMobListSubcommand(
    name = "goal",
    collection = GoalObjectCollection,
    addToMob = { mob, any -> mob.properties.addGoal(any as SerializableGoal )},
    grabFromMob = { mob -> mob.properties.goals },
    displayName =  "Goals",
    unknownName =  "Unknown Goal",
    drop = "Goal",
    mobGoalSet
)