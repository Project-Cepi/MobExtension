package world.cepi.mob.mob

import kotlinx.serialization.KSerializer
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import world.cepi.mob.meta.MobMeta
import kotlin.reflect.KClass

internal object MobMetaMapSerializer : KSerializer<Map<KClass<out MobMeta>, MobMeta>> {

    val dataSerializer = ListSerializer(MobMeta.serializer())

    override val descriptor: SerialDescriptor = dataSerializer.descriptor
    override fun serialize(encoder: Encoder, value: Map<KClass<out MobMeta>, MobMeta>)
            = dataSerializer.serialize(encoder, value.values.toList())
    override fun deserialize(decoder: Decoder) = dataSerializer.deserialize(decoder).map {
        it::class to it
    }.toMap().toMutableMap()
}