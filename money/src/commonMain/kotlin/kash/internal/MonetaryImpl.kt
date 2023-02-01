package kash.internal

import formatter.Formatter
import formatter.NumberFormatter
import formatter.NumberFormatterOptions
import formatter.NumberFormatterRawOptions
import formatter.toFormatterOptions
import kash.Currency
import kash.Monetary
import kash.Money
import kash.MoneyFormatter
import kash.MoneyFormatterOptions
import kash.MoneyFormatterOptions.Companion.DEFAULT_DECIMALS_ABBREVIATED
import kash.MoneyFormatterOptions.Companion.DEFAULT_DECIMALS_UNABBREVIATED
import kash.MoneyFormatterRawOptions
import kash.MoneyRatio
import kash.exceptions.CurrencyMatchException
import kash.toFormatterOptions
import kotlinx.serialization.UseSerializers
import kotlinx.serialization.builtins.LongAsStringSerializer
import kotlin.js.JsExport

@PublishedApi
internal class MonetaryImpl(centsAsLong: ULong) : AbstractMonetaryValue(centsAsLong, Currency.UXX), Monetary {

    override operator fun plus(other: Monetary) = MonetaryImpl(centsAsLong + other.centsAsLong)

    override operator fun plus(other: Number) = plus(other.toDouble())

    override operator fun plus(other: Double) = MonetaryImpl(centsAsLong + (other.toULong() * 100uL))

    override operator fun minus(other: Monetary) = MonetaryImpl(centsAsLong - other.centsAsLong)

    override fun minus(other: Double) = MonetaryImpl(centsAsLong - (other.toULong() * 100uL))

    override fun minus(other: Number) = minus(other.toDouble())

    override operator fun times(quantity: Double) = MonetaryImpl((centsAsDouble * quantity).toULong())

    override operator fun times(quantity: Number) = times(quantity.toDouble())

    override operator fun div(quantity: Double) = MonetaryImpl((centsAsDouble / quantity).toULong())

    override operator fun div(quantity: Number) = div(quantity.toDouble())

    override fun compareTo(other: Monetary): Int = (this - other).centsAsInt

    override fun format(formatter: Formatter<Monetary>): String = formatter.format(this)

    override fun toFormattedString(): String = toFormattedString(NumberFormatterOptions())

    override fun toFormattedString(
        abbreviate: Boolean,
        prefix: String,
        postfix: String,
        decimals: Int,
        enforceDecimals: Boolean,
        decimalSeparator: String,
        thousandsSeparator: String
    ) = NumberFormatter(abbreviate, prefix, postfix, decimals, enforceDecimals, decimalSeparator, thousandsSeparator).format(amountAsDouble)

    override fun toFormattedString(options: NumberFormatterRawOptions): String = NumberFormatter(options.toFormatterOptions()).format(amountAsDouble)

    override fun toString() = toFormattedString(abbreviate = false)
}