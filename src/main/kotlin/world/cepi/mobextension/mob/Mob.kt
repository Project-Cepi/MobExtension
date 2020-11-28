package world.cepi.mobextension.mob

import net.minestom.server.chat.ChatColor
import net.minestom.server.chat.ColoredText
import net.minestom.server.data.DataImpl
import net.minestom.server.entity.Entity
import net.minestom.server.entity.EntityType
import net.minestom.server.event.player.PlayerBlockInteractEvent
import net.minestom.server.item.ItemStack
import net.minestom.server.item.Material
import net.minestom.server.utils.Position
import kotlin.reflect.full.primaryConstructor

class Mob(
        val type: EntityType = EntityType.LLAMA
) {

    companion object {
        const val mobKey = "mob-key"
    }

    val meta: MutableList<MobMeta<*>> = mutableListOf()

    fun generateMob(position: Position): Entity? {
        mobTypeList.firstOrNull { it.second == type }?.let { entityClassPair ->
            return entityClassPair.first.primaryConstructor!!.call(position)
        }

        return null

    }

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

fun mobSpawnEvent(event: PlayerBlockInteractEvent) {
    val item = event.player.itemInMainHand
    if (item.data?.get<Mob>(Mob.mobKey) == null) return

    val mobData = item.data?.get<Mob>(Mob.mobKey)

    val mobEntity = mobData!!.generateMob(event.blockPosition.add(0, 1, 0).toPosition())
    mobEntity!!.spawn()
}