package world.cepi.mob.commands.subcommands.edit

import world.cepi.kepi.messages.translations.formatTranslableMessage
import world.cepi.mob.goal.SerializableGoal

internal object GoalSubcommand: GenericMobListSubcommand(
    name = "goal",
    sealedClass = SerializableGoal::class,
    addToMob = { any -> add(any as SerializableGoal )},
    clearList = { copy(goals = emptyList()) },
    removeAt = { index -> copy(goals = goals.toMutableList().also { it.removeAt(index) }) },
    grab = { goals },
    displayName =  "Goals",
    unknownName =  "Unknown Goal",
    drop = "Goal",
    { sender, component -> sender.formatTranslableMessage("mob", "goal.add", component) },
    """
        Goals define the behavior
        that should be enacted when specific conditions are met.
        
        For example, the meleeattack goal
        says that when a <blue>target<gray> is in the attack radius,
        attack it every <blue>(update option, EX 1t(ick), 5d(ays)).
        
        Goals are also ordered in priority, EX to make a basic zombie,
        you need to add the melee attack goal first,
        then the random move goal, making the "move" goal the melee's fallback.
        
        Both Goals and Targets have the same syntax:
        
        <yellow>/mob goal add (goal) (...params)
        <yellow>/mob goal list
        <yellow>/mob goal info (goal)
        
        <yellow>TIP: <gray>Press space after the <blue>(goal)
        parameter to see its next paramaters (ex meleeattack 1 1t)
    """.trimIndent()

)