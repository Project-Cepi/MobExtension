package world.cepi.mob.commands.subcommands.edit

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import net.minestom.server.command.builder.Command
import net.minestom.server.command.builder.arguments.ArgumentType
import net.minestom.server.command.builder.exception.ArgumentSyntaxException
import net.minestom.server.entity.Player
import world.cepi.kepi.messages.sendFormattedTranslatableMessage
import world.cepi.kepi.subcommands.Help
import world.cepi.kstom.command.addSyntax
import world.cepi.kstom.command.arguments.argumentsFromClass
import world.cepi.kstom.command.arguments.literal
import world.cepi.mob.commands.MobCommand
import world.cepi.mob.meta.MobMeta
import world.cepi.mob.mob.mobEgg

internal object MetaSubcommand : Command("meta") {

    init {

        val set = "set".literal()
        val remove = "remove".literal()

        val metaClass = ArgumentType.Word("metaName").from(
            *MobMeta::class.sealedSubclasses
                .map { it.simpleName!!.lowercase().dropLast(this.name.length) }
                .toTypedArray()
        ).map { name -> MobMeta::class.sealedSubclasses
            .firstOrNull { it.simpleName!!.lowercase().dropLast(this.name.length) == name }
            ?: throw ArgumentSyntaxException("Meta is invalid", name, 1)
        }

        MobMeta::class.sealedSubclasses.forEach { clazz ->
            val arguments = argumentsFromClass(clazz)

            val clazzArgumentName = clazz.simpleName!!.lowercase().dropLast(4)

            addSyntax(set, clazzArgumentName.literal(), *arguments.args) { sender, args ->
                if (!MobCommand.hasMobEgg(sender)) return@addSyntax

                val player = sender as Player

                val mob = player.mobEgg ?: return@addSyntax

                val metaArg = arguments.createInstance(args, sender)

                mob.addMeta(metaArg)

                player.itemInMainHand = mob.generateEgg()
            }

        }

        addSyntax(remove, metaClass) { sender, args ->
            if (!MobCommand.hasMobEgg(sender)) return@addSyntax

            val player = sender as Player

            val mob = player.mobEgg ?: return@addSyntax

            if (mob.metas.values.any { it::class == args.get(metaClass) }) {

                mob.metas.remove(args.get(metaClass))

                player.itemInMainHand = mob.generateEgg()

                player.sendFormattedTranslatableMessage(
                    "mob", "meta.add",
                    Component.text(
                        args.get(metaClass).simpleName!!.lowercase().dropLast(name.length),
                        NamedTextColor.BLUE
                    )
                )
            }
        }

        addSubcommand(
            Help(
                """
                    Mob meta allows you to define
                    certain <blue>properties<gray> of a mob.
                    
                    It can be either display or behavior meta.
                    
                    Display meta can be colors or the size of the mob,
                    while behavior meta can be health or knockback.
                    
                    To set a meta, just do
                    <yellow>/mob meta set <meta> <value>
                    EX: /mob meta set health 5
                    
                    To remove a meta, do:
                    <yellow>/mob meta remove <meta>
                    EX: <yellow>/mob meta remove health
                """.trimIndent()
            )
        )
    }

}