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
import world.cepi.mobextension.api.goals.Goal
import kotlin.reflect.full.primaryConstructor
import world.cepi.mobextension.mob.conditional.ConditionalHolder
class Mob(val properties: Properties): ConditionalHolder() {

    companion object {
        const val mobKey = "mob-key"
    }

    fun generateMob(position: Position): Entity? {
        mobTypeList.firstOrNull { it.second == properties.type }?.let { entityClassPair ->
            return entityClassPair.first.primaryConstructor!!.call(position)
        }

        return null

    }

    fun generateEgg(): ItemStack {

        val mobEgg = ItemStack(Material.LLAMA_SPAWN_EGG, 1)
        mobEgg.displayName = ColoredText.of(ChatColor.GOLD, "Mob Creation Egg")
        val data = DataImpl()

        data.set(mobKey, this)

        mobEgg.data = data
        return mobEgg

    }

    class Properties {
        val goals = mutableListOf<Goal>()
        val meta = mutableListOf<MobMeta<*>>()
        lateinit var type: EntityType

        fun addGoal(goal: Goal): Properties {
            goals.add(goal)
            return this
        }

        fun addMeta(metaArg: MobMeta<*>): Properties {
            meta.add(metaArg)
            return this
        }

        fun setType(typeToSet: EntityType): Properties {
            type = typeToSet
            return this
        }
    }


}

fun mobSpawnEvent(event: PlayerBlockInteractEvent) {
    val item = event.player.itemInMainHand
    if (item.data?.get<Mob>(Mob.mobKey) == null) return

    val mobData = item.data?.get<Mob>(Mob.mobKey)

    val mobEntity = mobData!!.generateMob(event.blockPosition.add(0, 1, 0).toPosition())
    mobEntity!!.spawn()
}