@file:UseSerializers(LongAsStringSerializer::class)

package kash

import kotlinx.serialization.Serializable
import kotlinx.serialization.UseSerializers
import kotlinx.serialization.builtins.LongAsStringSerializer

@Serializable
data class Money(
    /** In the lowest denomination */
    val amount: ULong,
    val currency: Currency
) {
    val readableValue get() = amount.toDouble() / currency.lowestDenomination.toDouble()
    
    val readableString get() = "${currency.name} $readableValue"
}