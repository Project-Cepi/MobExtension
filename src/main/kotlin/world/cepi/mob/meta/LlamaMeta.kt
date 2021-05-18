package world.cepi.mob.meta

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import net.minestom.server.entity.Entity
import net.minestom.server.entity.metadata.animal.LlamaMeta

@Serializable
@SerialName("llama-color")
data class LlamaColorMeta(@SerialName("value") val variant: LlamaMeta.Variant) : MobMeta() {
    override fun apply(entity: Entity) {
        (entity.entityMeta as? LlamaMeta ?: return).variant = variant
    }
}