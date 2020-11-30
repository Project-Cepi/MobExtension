package world.cepi.mobextension.mob.meta

import net.minestom.server.chat.ColoredText
import net.minestom.server.entity.Entity

class NameMeta(val name: String) : MobMeta {
    override fun apply(entity: Entity) {
        entity.customName = ColoredText.of(name)
    }
}