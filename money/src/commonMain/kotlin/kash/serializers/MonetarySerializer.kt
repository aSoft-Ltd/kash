package kash.serializers

import kash.Monetary
import kotlinx.serialization.Serializer
import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

@Serializer(forClass = Monetary::class)
object MonetarySerializer : KSerializer<Monetary> {
    override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor("kash.Monetary", PrimitiveKind.DOUBLE)
    override fun deserialize(decoder: Decoder): Monetary = Monetary(decoder.decodeDouble())
    override fun serialize(encoder: Encoder, value: Monetary) = encoder.encodeDouble(value.amountAsDouble)
}