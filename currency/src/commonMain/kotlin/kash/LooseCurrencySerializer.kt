package kash

import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

//@Serializer(forClass = Currency::class)
object LooseCurrencySerializer : KSerializer<Currency> {
    override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor("kash.Currency", PrimitiveKind.STRING)
    override fun serialize(encoder: Encoder, value: Currency) = encoder.encodeString(value.name)
    override fun deserialize(decoder: Decoder): Currency {
        val value = decoder.decodeString()
        val currencies = Currency.values
        return currencies.firstOrNull {
            it.name == value
        } ?: currencies.firstOrNull {
            it.globalSymbol == value
        } ?: currencies.firstOrNull {
            it.localSymbol == value
        } ?: Currency.UXX
    }
}