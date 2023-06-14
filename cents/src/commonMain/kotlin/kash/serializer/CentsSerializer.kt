package kash.serializer

import kash.Cents
import kash.cents
import kotlinx.serialization.KSerializer
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

object CentsSerializer : KSerializer<Cents> {
    override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor("kash.Cents", PrimitiveKind.LONG)

    override fun deserialize(decoder: Decoder): Cents = decoder.decodeSerializableValue(ULong.serializer()).cents

    override fun serialize(encoder: Encoder, value: Cents) = encoder.encodeSerializableValue(ULong.serializer(), value.asULong)
}