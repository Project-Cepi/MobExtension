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

class MobCommand : Command("mob") {
    init {

        setCondition { sender, _ ->
            if (!sender.isPlayer) {
                sender.sendMessage("Only players can use this command!")
                return@setCondition false
            } else return@setCondition true
        }

        addSyntax({ sender, _ ->
            val player = sender as Player
            val mobEgg = ItemStack(Material.LLAMA_SPAWN_EGG, 1)
            mobEgg.displayName = ColoredText.of(ChatColor.GOLD, "Mob Creation Egg")
            val data = DataImpl()

            data.set("id", genRandomID(), String::class.java)
            data.set("type", EntityType.LLAMA, EntityType::class.java)
            data.set("ai", false, Boolean::class.java)
            data.set("speed", 0F, Float::class.java)
            data.set("health", 20F, Float::class.java)
            data.set("name", "", String::class.java)

            mobEgg.lore = arrayListOf(
                    ColoredText.of("id: ${ChatColor.CYAN}${data.get<String>("id")}"),
                    ColoredText.of("AI: ${ChatColor.DARK_RED}NO"),
                    ColoredText.of("Speed: ${ChatColor.BLUE}0"),
                    ColoredText.of("Health: ${ChatColor.RED}20 ❤️") // There is a heart emoji there even if you can't see it,
            )

            mobEgg.data = data
            player.inventory.addItemStack(mobEgg)
        }, ArgumentType.Word("create").from("create"))

    }
}