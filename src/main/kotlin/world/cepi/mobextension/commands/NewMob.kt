package world.cepi.mobextension.commands

import net.minestom.server.chat.ChatColor
import net.minestom.server.chat.ColoredText
import net.minestom.server.command.CommandProcessor
import net.minestom.server.command.CommandSender
import net.minestom.server.data.DataImpl
import net.minestom.server.entity.EntityType
import net.minestom.server.entity.Player
import net.minestom.server.item.ItemStack
import net.minestom.server.item.Material
import world.cepi.mobextension.genRandomID

class NewMob: CommandProcessor {
    override fun getCommandName(): String = "newmob"

    override fun getAliases(): Array<String>? = arrayOf()

    override fun process(sender: CommandSender, command: String, args: Array<out String>): Boolean {
        if (sender !is Player) return false
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
        sender.inventory.addItemStack(mobEgg)
        return true
    }

    override fun hasAccess(player: Player): Boolean = true
}