package world.cepi.mob.mob.player

import net.kyori.adventure.text.Component
import net.minestom.server.entity.EntityCreature
import net.minestom.server.entity.EntityType
import net.minestom.server.entity.GameMode
import net.minestom.server.entity.Player
import net.minestom.server.network.packet.server.play.PlayerInfoPacket

class PlayerMob(type: EntityType, val name: String = "Mob") : EntityCreature(type) {

    override fun updateNewViewer(player: Player) {
        val packet = PlayerInfoPacket(PlayerInfoPacket.Action.ADD_PLAYER,
            PlayerInfoPacket.AddPlayer(
                uuid, name,
                listOf(),
                GameMode.SURVIVAL,
                0,
                Component.text(name)
            )
        )


        player.playerConnection.sendPacket(packet)

        return super.updateNewViewer(player)
    }

    override fun updateOldViewer(player: Player) {

        val packet = PlayerInfoPacket(PlayerInfoPacket.Action.REMOVE_PLAYER, PlayerInfoPacket.RemovePlayer(
            uuid
        ))


        player.playerConnection.sendPacket(packet)

        return super.updateOldViewer(player)
    }
}