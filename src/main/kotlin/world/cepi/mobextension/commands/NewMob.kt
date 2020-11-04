package world.cepi.mobextension.commands

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
        val mobEgg = ItemStack(Material.CHICKEN_SPAWN_EGG, 1)
        val data = DataImpl()

        data.set("id", genRandomID(), String::class.java)
        data.set("type", EntityType.CHICKEN, EntityType::class.java)
        data.set("ai", false, Boolean::class.java)

        mobEgg.data = data
        sender.inventory.addItemStack(mobEgg)
        return true
    }

    override fun hasAccess(player: Player): Boolean = true
}