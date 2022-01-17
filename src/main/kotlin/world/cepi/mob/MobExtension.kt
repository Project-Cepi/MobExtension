package world.cepi.mob

import net.minestom.server.entity.EntityCreature
import net.minestom.server.entity.EntityType
import net.minestom.server.entity.ai.EntityAIGroup
import net.minestom.server.entity.ai.goal.MeleeAttackGoal
import net.minestom.server.entity.ai.target.ClosestEntityTarget
import net.minestom.server.event.EventFilter
import net.minestom.server.event.EventNode
import net.minestom.server.event.player.PlayerSpawnEvent
import net.minestom.server.extensions.Extension
import net.minestom.server.utils.time.TimeUnit
import world.cepi.kstom.Manager
import world.cepi.kstom.event.listenOnly
import world.cepi.kstom.util.log
import world.cepi.kstom.util.node
import world.cepi.mob.commands.MobCommand
import world.cepi.mob.events.MobInteractHook
import world.cepi.mob.events.MobSpawnHook
import world.cepi.mob.events.MobUIHook
import world.cepi.mob.mob.Mob
import world.cepi.mob.spawner.MobSpawner
import java.io.File

class MobExtension : Extension() {
    override fun initialize(): LoadStatus {

        val playerNode = EventNode.type("general-mob-hooks", EventFilter.PLAYER)

        playerNode.listenOnly(MobSpawnHook::hook)
        playerNode.listenOnly(MobSpawnHook::hookInteract)
        playerNode.listenOnly(MobUIHook::hookDig)
        playerNode.listenOnly(MobUIHook::hookAnimation)
        playerNode.listenOnly(MobInteractHook::hook)

        node.addChild(MobSpawner.allNode)

        node.addChild(playerNode)

        MobCommand.register()

        log.info("[MobExtension] has been enabled!")

        // TODO seperate Combat module from its ItemExtension
//        val itemExtension = Manager.extension.getExtension("ItemExtension")
//
//        if (itemExtension != null)
//            itemExtension.node.addChild(Mob.mobEventNode)
//        else
//            node.addChild(Mob.mobEventNode)

        return LoadStatus.SUCCESS
    }

    override fun terminate() {

        MobCommand.unregister()

        log.info("[MobExtension] has been disabled!")
    }

    companion object {
        val dataDir: File get() {
            val dir = File("./extensions/MobExtension")
            if (!dir.exists()) dir.mkdirs()
            return dir
        }
    }
}