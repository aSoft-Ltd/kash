@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package kash

import formatter.Formatter
import formatter.NumberFormatterRawOptions
import kotlinx.serialization.SerialName
import kotlin.js.JsExport
import kotlin.js.JsName

interface MonetaryValue {
    /** In the lowest denomination */
    @SerialName("cents")
    val centsAsLong: ULong

    val centsAsInt: Int

    val centsAsDouble: Double

    val amountAsLong: Long

    val amountAsInt: Int

    val amountAsDouble: Double

    fun toFormattedString(): String

    fun formatValue(formatter: Formatter<MonetaryValue>): String

    fun with(currency: Currency): Money

    @JsName("toFormattedStringWith")
    fun toFormattedString(options: NumberFormatterRawOptions): String
}