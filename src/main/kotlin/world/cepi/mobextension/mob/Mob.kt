package world.cepi.mobextension.mob

import net.minestom.server.chat.ChatColor
import net.minestom.server.chat.ColoredText
import net.minestom.server.data.DataImpl
import net.minestom.server.entity.EntityType
import net.minestom.server.item.ItemStack
import net.minestom.server.item.Material
import world.cepi.mobextension.genRandomID

class Mob(
        val id: String = genRandomID(),
        val type: EntityType = EntityType.LLAMA
) {

    companion object {
        const val mobKey = "mob-key"
    }

    val meta: MutableList<MobMeta<*>> = mutableListOf()

    fun generateEgg(): ItemStack {

        val mobEgg = ItemStack(Material.LLAMA_SPAWN_EGG, 1)
        mobEgg.displayName = ColoredText.of(ChatColor.GOLD, "Mob Creation Egg")
        val data = DataImpl()

        data.set(mobKey, this)

        mobEgg.lore = arrayListOf(
                ColoredText.of("id: ${ChatColor.CYAN}${data.get<String>("id")}")
        )

        mobEgg.data = data
        return mobEgg

    }

}