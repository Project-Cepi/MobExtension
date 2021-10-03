package world.cepi.mob.mob.player

import net.minestom.server.entity.EntityCreature
import net.minestom.server.entity.EntityType
import net.minestom.server.entity.GameMode
import net.minestom.server.entity.Player
import net.minestom.server.network.packet.server.play.PlayerInfoPacket

class PlayerMob(type: EntityType, val name: String = "Mob") : EntityCreature(type) {

    override fun addViewer0(player: Player): Boolean {
        val packet = PlayerInfoPacket(PlayerInfoPacket.Action.ADD_PLAYER)

        packet.playerInfos.add(
            PlayerInfoPacket.AddPlayer(
            uuid, name,
            GameMode.SURVIVAL,
            0
        ))


        player.playerConnection.sendPacket(packet)

        return super.addViewer0(player)
    }

    override fun removeViewer0(player: Player): Boolean {

        val packet = PlayerInfoPacket(PlayerInfoPacket.Action.REMOVE_PLAYER)

        packet.playerInfos.add(
            PlayerInfoPacket.RemovePlayer(
            uuid
        ))


        player.playerConnection.sendPacket(packet)

        return super.removeViewer0(player)
    }
}