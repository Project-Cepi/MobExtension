package world.cepi.mobextension.commands

import net.minestom.server.chat.ChatColor
import net.minestom.server.chat.ColoredText
import net.minestom.server.command.builder.Command
import net.minestom.server.command.builder.arguments.ArgumentType
import net.minestom.server.data.DataImpl
import net.minestom.server.entity.EntityType
import net.minestom.server.entity.Player
import net.minestom.server.item.ItemStack
import net.minestom.server.item.Material
import world.cepi.mobextension.genRandomID
import world.cepi.mobextension.mob.Mob

class MobCommand : Command("mob") {
    init {

        setCondition { sender, _ ->
            if (!sender.isPlayer) {
                sender.sendMessage("Only players can use this command!")
                return@setCondition false
            } else return@setCondition true
        }

        val createSubcommand = ArgumentType.Word("create").from("create")
        val metaSubcommand = ArgumentType.Word("meta").from("meta")

        addSyntax({ sender, _ ->
            val player = sender as Player
            val mobEgg = ItemStack(Material.LLAMA_SPAWN_EGG, 1)
            mobEgg.displayName = ColoredText.of(ChatColor.GOLD, "Mob Creation Egg")
            val data = DataImpl()
            val mob = Mob()

            data.set("mob-item", mob)

            mobEgg.lore = arrayListOf(
                    ColoredText.of("id: ${ChatColor.CYAN}${data.get<String>("id")}")
            )

            mobEgg.data = data
            player.inventory.addItemStack(mobEgg)
        }, createSubcommand)

    }
}