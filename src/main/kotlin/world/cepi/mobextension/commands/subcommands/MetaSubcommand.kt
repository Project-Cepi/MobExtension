package world.cepi.mobextension.commands.subcommands

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import net.minestom.server.command.builder.Command
import net.minestom.server.command.builder.arguments.ArgumentType
import net.minestom.server.command.builder.exception.ArgumentSyntaxException
import net.minestom.server.entity.Player
import world.cepi.kepi.messages.sendFormattedTranslatableMessage
import world.cepi.kstom.command.addSyntax
import world.cepi.kstom.command.arguments.argumentsFromClass
import world.cepi.kstom.command.arguments.literal
import world.cepi.mobextension.commands.MobCommand
import world.cepi.mobextension.meta.MetaObjectCollection
import world.cepi.mobextension.mob
import kotlin.reflect.full.primaryConstructor

internal object MetaSubcommand : Command("meta") {

    init {

        val set = "set".literal()
        val remove = "remove".literal()

        val metaClass = ArgumentType.Word("metaName").from(
            *MetaObjectCollection.objects
                .map { it.simpleName!!.lowercase().dropLast(this.name.length) }
                .toTypedArray()
        ).map { name -> MetaObjectCollection
            .objects
            .firstOrNull { it.simpleName!!.lowercase().dropLast(this.name.length) == name }
            ?: throw ArgumentSyntaxException("Meta is invalid", name, 1)
        }

        MetaObjectCollection.objects.forEach { clazz ->
            val arguments = argumentsFromClass(clazz)

            val clazzArgumentName = clazz.simpleName!!.lowercase().dropLast(4)

            addSyntax(set, clazzArgumentName.literal(), *arguments.args) { sender, args ->
                if (!MobCommand.hasMobEgg(sender)) return@addSyntax

                val player = sender as Player

                val mob = player.mob ?: return@addSyntax

                val metaArg = arguments.createInstance(args)

                mob.properties.addMeta(metaArg)

                player.itemInMainHand = mob.generateEgg()
            }

        }

        addSyntax(remove, metaClass) { sender, args ->
            if (!MobCommand.hasMobEgg(sender)) return@addSyntax

            val player = sender as Player

            val mob = player.mob ?: return@addSyntax

            if (mob.properties.metas.values.any { it::class == args.get(metaClass) }) {

                mob.properties.metas.remove(args.get(metaClass))

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
    }

}