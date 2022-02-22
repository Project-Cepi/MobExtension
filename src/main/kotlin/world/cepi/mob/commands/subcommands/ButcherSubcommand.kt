package world.cepi.mob.commands.subcommands

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import net.minestom.server.command.builder.arguments.ArgumentType
import net.minestom.server.entity.Entity
import net.minestom.server.entity.Player
import net.minestom.server.utils.entity.EntityFinder
import world.cepi.kepi.command.subcommand.applyHelp
import world.cepi.kepi.messages.sendFormattedTranslatableMessage
import world.cepi.kstom.command.kommand.Kommand
import world.cepi.particle.Particle
import world.cepi.particle.ParticleType
import world.cepi.particle.data.OffsetAndSpeed
import world.cepi.particle.extra.Dust
import world.cepi.particle.renderer.Renderer
import world.cepi.particle.renderer.render
import java.util.function.Supplier

object ButcherSubcommand : Kommand({

    val radius = ArgumentType.Integer("radius").min(1).max(100)
    val finder = ArgumentType.Entity("entities")

    finder.defaultValue = Supplier {
        EntityFinder().setTargetSelector(EntityFinder.TargetSelector.ALL_ENTITIES)
    }

    applyHelp {
        """
            The butcher helper command allows you to remove 
            non-player mobs in a nearby radius (up to 100)
            
            You can specify a vanilla selector
            EX: <blue>@e, @e[type=wolf,limit=50,sort=nearest]
            
            Syntax:
            <yellow>/mob butcher (radius: 1-100) (selector)
        """.trimIndent()
    }

    syntax(radius, finder).onlyPlayers {
        val foundEntities = context.get(finder).find(player)
            .filter { it !is Player } // no players
            .filter { it.getDistance(player) <= context.get(radius) } // at that distance

        foundEntities.forEach {
            it.position.asVec().render(Particle.particle(
                type = ParticleType.DUST,
                count = 1,
                data = OffsetAndSpeed(0f, 0f, 0f, 0f),
                extraData = Dust(1f, 0f, 0f, scale = 1f)
            ), it.viewersAsAudience)
            it.remove()
        }

        player.sendFormattedTranslatableMessage(
            "mob", if (foundEntities.size == 1) "butcher.single" else "butcher.plural",
            Component.text(foundEntities.size, NamedTextColor.BLUE),
        )

    }


}, "butcher")