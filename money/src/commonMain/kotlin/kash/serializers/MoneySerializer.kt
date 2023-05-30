@file:UseSerializers(LongAsStringSerializer::class)

package kash.serializers

import kash.Currency
import kash.LooseCurrencySerializer
import kash.Money
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.Serializer
import kotlinx.serialization.UseSerializers
import kotlinx.serialization.builtins.LongAsStringSerializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.buildClassSerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

@Serializer(forClass = Money::class)
object MoneySerializer : KSerializer<Money> {

    override val descriptor: SerialDescriptor = buildClassSerialDescriptor("kash.Money")

    override fun deserialize(decoder: Decoder) = decoder.decodeSerializableValue(Pesa.serializer()).toMoney()

    override fun serialize(encoder: Encoder, value: Money) {
        encoder.encodeSerializableValue(Pesa.serializer(), value.toPesa())
    }

    @Serializable
    private class Pesa(
        val cents: ULong,
        @Serializable(with = LooseCurrencySerializer::class)
        val currency: Currency
    )

    private inline fun Money.toPesa() = Pesa(centsAsLong, currency)
    private inline fun Pesa.toMoney() = Money(cents, currency)
}