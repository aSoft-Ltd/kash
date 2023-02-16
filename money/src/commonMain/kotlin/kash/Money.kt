@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package kash

import formatter.NumberFormatter
import formatter.NumberFormatterRawOptions
import kash.MoneyFormatterOptions.Companion.DEFAULT_ABBREVIATE
import kash.MoneyFormatterOptions.Companion.DEFAULT_DECIMAL_SEPARATOR
import kash.MoneyFormatterOptions.Companion.DEFAULT_ENFORCE_DECIMALS
import kash.MoneyFormatterOptions.Companion.DEFAULT_POSTFIX
import kash.MoneyFormatterOptions.Companion.DEFAULT_PREFIX
import kash.MoneyFormatterOptions.Companion.DEFAULT_THOUSAND_SEPERATOR
import kash.serializers.MoneySerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlin.js.JsExport
import kotlin.js.JsName

@Serializable(with = MoneySerializer::class)
interface Money : Comparable<Money> {
    //cents
    /** In the lowest denomination */
    @SerialName("cents")
    val centsAsLong: ULong

    val centsAsInt: Int

    val centsAsDouble: Double

    // amounts
    val amountAsLong: Long

    val amountAsInt: Int

    val amountAsDouble: Double

    val currency: Currency

    // mappers
    fun with(currency: Currency): Money

    fun toMonetary(): Monetary

    // formatters
    fun toFormattedString(): String

    fun format(formatter: NumberFormatter): String

    @JsName("toFormattedStringWith")
    fun toFormattedString(options: NumberFormatterRawOptions): String

    @JsName("formatWithMoneyFormatter")
    fun format(formatter: MoneyFormatter): String

    @JsName("_ignore_toFormattedString")
    fun toFormattedString(
        abbreviate: Boolean = DEFAULT_ABBREVIATE,
        prefix: String = DEFAULT_PREFIX,
        postfix: String = DEFAULT_POSTFIX,
        decimals: Int? = null,
        enforceDecimals: Boolean = DEFAULT_ENFORCE_DECIMALS,
        decimalSeparator: String = DEFAULT_DECIMAL_SEPARATOR,
        thousandsSeparator: String = DEFAULT_THOUSAND_SEPERATOR
    ): String

    // arithmetics
    operator fun plus(other: Money): Money

    operator fun minus(other: Money): Money

    operator fun times(quantity: Double): Money

    @JsName("_ignore_times_number")
    operator fun times(quantity: Number): Money

    operator fun div(quantity: Double): Money

    @JsName("_ignore_div")
    operator fun div(quantity: Number): Money

    @JsName("ratio")
    operator fun div(other: Money): MoneyRatio

    // comparators
    override fun compareTo(other: Money): Int
}