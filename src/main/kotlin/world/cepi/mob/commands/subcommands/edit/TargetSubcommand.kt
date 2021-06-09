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
    { sender, component -> sender.formatTranslableMessage("mob", "target.add", component) },
    """
        Targets define
        what things this mob should look for (attacking, healing, etc.)
        
        For example, the closestplayer target
        will find the nearest player in its <blue>(radius)
        
        Targets are also ordered in priority, EX it can
        prioritize players over regular mobs by adding the
        <blue>player<gray> target first.
        
        Both Goals and Targets have the same syntax:
        
        <yellow>/mob target add (target) (...params)
        <yellow>/mob target list
        <yellow>/mob target info (target)
        
        <yellow>TIP: <gray>Press space after the <blue>(target)
        parameter to see its params
    """.trimIndent()

)