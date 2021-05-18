package world.cepi.mob.commands.subcommands.edit

import world.cepi.kepi.messages.translations.formatTranslableMessage
import world.cepi.mob.goal.SerializableGoal

internal object GoalSubcommand: GenericMobListSubcommand(
    name = "goal",
    sealedClass = SerializableGoal::class,
    addToMob = { mob, any -> mob.addGoal(any as SerializableGoal )},
    grabFromMob = { mob -> mob.goals },
    displayName =  "Goals",
    unknownName =  "Unknown Goal",
    drop = "Goal",
    { sender -> sender.formatTranslableMessage("mob", "goal.add") }
)