package world.cepi.mob.mob

import kotlinx.serialization.KSerializer
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import world.cepi.mob.property.MobProperty
import kotlin.reflect.KClass

internal object MobPropertyMapSerializer : KSerializer<MutableMap<KClass<out MobProperty>, MobProperty>> {

    val dataSerializer = ListSerializer(MobProperty.serializer())

    override val descriptor: SerialDescriptor = dataSerializer.descriptor
    override fun serialize(encoder: Encoder, value: MutableMap<KClass<out MobProperty>, MobProperty>)
            = dataSerializer.serialize(encoder, value.values.toList())
    override fun deserialize(decoder: Decoder) = dataSerializer.deserialize(decoder).map {
        it::class to it
    }.toMap().toMutableMap()
}