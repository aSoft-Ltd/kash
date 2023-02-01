@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package kash

import formatter.Formatter
import formatter.NumberFormatterOptions
import formatter.NumberFormatterRawOptions
import kash.serializers.MonetarySerializer
import kotlinx.serialization.Serializable
import kotlin.js.JsExport
import kotlin.js.JsName

@Serializable(with = MonetarySerializer::class)
interface Monetary : MonetaryValue, Arithmetic<Monetary> {

    @JsName("_ignore_plusNumber")
    operator fun plus(other: Number): Monetary

    @JsName("plusNumber")
    operator fun plus(other: Double): Monetary

    @JsName("_ignore_minusNumber")
    operator fun minus(other: Number): Monetary

    @JsName("timesNumber")
    operator fun minus(other: Double): Monetary

    fun format(formatter: Formatter<Monetary>): String = formatter.format(this)

    @JsName("_ignore_toFormattedString")
    fun toFormattedString(
        abbreviate: Boolean = NumberFormatterOptions.DEFAULT_ABBREVIATE,
        prefix: String = NumberFormatterOptions.DEFAULT_PREFIX,
        postfix: String = NumberFormatterOptions.DEFAULT_POSTFIX,
        decimals: Int = NumberFormatterOptions.DEFAULT_DECIMALS_ABBREVIATED,
        enforceDecimals: Boolean = NumberFormatterOptions.DEFAULT_ENFORCE_DECIMALS,
        decimalSeparator: String = NumberFormatterOptions.DEFAULT_DECIMAL_SEPARATOR,
        thousandsSeparator: String = NumberFormatterOptions.DEFAULT_THOUSAND_SEPERATOR
    ): String
}