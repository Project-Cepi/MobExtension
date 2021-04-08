package world.cepi.mobextension.commands.subcommands

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import net.minestom.server.command.builder.Command
import net.minestom.server.command.builder.arguments.ArgumentType
import net.minestom.server.entity.Player
import world.cepi.kepi.messages.sendFormattedMessage
import world.cepi.kepi.messages.sendFormattedTranslatableMessage
import world.cepi.kstom.command.addSyntax
import world.cepi.kstom.command.arguments.argumentsFromConstructor
import world.cepi.kstom.command.arguments.asSubcommand
import world.cepi.mobextension.commands.MobCommand
import world.cepi.mobextension.commands.mobMetaSet
import world.cepi.mobextension.meta.MetaObjectCollection
import world.cepi.mobextension.mob
import kotlin.reflect.full.primaryConstructor

internal object MetaSubcommand : Command("meta") {

    init {

        val set = "set".asSubcommand()
        val remove = "remove".asSubcommand()

        val metaNames = ArgumentType.Word("metaName").from(
            *MetaObjectCollection.objects
                .map { it.simpleName!!.toLowerCase().dropLast(name.length) }
                .toTypedArray()
        )

        MetaObjectCollection.objects.forEach { clazz ->
            val arguments = argumentsFromConstructor(clazz.primaryConstructor!!)

            val clazzArgumentName = clazz.simpleName!!.toLowerCase().dropLast(4)

            addSyntax(set, clazzArgumentName.asSubcommand(), *arguments.toTypedArray()) { sender, args ->
                if (!MobCommand.hasMobEgg(sender)) return@addSyntax

                val player = sender as Player

                val mob = player.mob ?: return@addSyntax

                val metaArg = clazz.primaryConstructor!!.call(*arguments.map { args.get(it) }.toTypedArray())

                mob.properties.addMeta(metaArg)

                player.itemInMainHand = mob.generateEgg()
            }

        }

        addSyntax(remove, metaNames) { sender, args ->
            if (!MobCommand.hasMobEgg(sender)) return@addSyntax

            val player = sender as Player

            val mob = player.mob ?: return@addSyntax

            if (mob.properties.metas.values.any { it::class.simpleName!!.toLowerCase().dropLast(name.length) == args.get(metaNames) }) {

                mob.properties.metas.remove(
                    mob.properties.metas.keys
                        .firstOrNull { it.simpleName!!.toLowerCase().dropLast(name.length) == args.get(metaNames) }
                )

                player.itemInMainHand = mob.generateEgg()

                player.sendFormattedTranslatableMessage("mob", "meta.add", Component.text(args.get(metaNames), NamedTextColor.BLUE))
            }
        }
    }

}