@file:JsExport
@file:UseSerializers(LongAsStringSerializer::class)
@file:Suppress("NON_EXPORTABLE_TYPE")

package kash.internal

import formatter.Formatter
import kash.Currency
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
internal class MoneyImpl(centsAsLong: ULong, currency: Currency) : AbstractMonetaryValue(centsAsLong, currency), Money {
    override val currency: Currency = currency

    private fun currencyCheckFor(op: String, other: Money) {
        if (other.currency != currency) {
            throw CurrencyMatchException(currency, op, other.currency)
        }
    }

    override operator fun plus(other: Money): MoneyImpl {
        currencyCheckFor("addition", other)
        return MoneyImpl(centsAsLong + other.centsAsLong, currency)
    }

    override operator fun minus(other: Money): MoneyImpl {
        currencyCheckFor("subtraction", other)
        return MoneyImpl(centsAsLong - other.centsAsLong, currency)
    }

    override operator fun times(quantity: Double) = MoneyImpl((centsAsDouble * quantity).toULong(), currency)

    override operator fun times(quantity: Number) = times(quantity.toDouble())

    override operator fun div(quantity: Double) = MoneyImpl((centsAsDouble / quantity).toULong(), currency)

    override operator fun div(quantity: Number) = div(quantity.toDouble())

    override operator fun div(other: Money) = MoneyRatio((centsAsDouble / other.centsAsDouble), currency, other.currency)

    override fun compareTo(other: Money): Int {
        currencyCheckFor("comparison", other)
        return (this - other).centsAsInt
    }

    override fun format(formatter: Formatter<Money>): String = formatter.format(this)

    override fun toFormattedString(): String = toFormattedString(MoneyFormatterOptions())

    override fun toFormattedString(
        abbreviate: Boolean,
        prefix: String,
        postfix: String,
        decimals: Int?,
        enforceDecimals: Boolean,
        decimalSeparator: String,
        thousandsSeparator: String
    ) = MoneyFormatter(
        abbreviate,
        prefix,
        postfix,
        decimals = decimals ?: if (abbreviate) DEFAULT_DECIMALS_ABBREVIATED else DEFAULT_DECIMALS_UNABBREVIATED,
        enforceDecimals,
        decimalSeparator,
        thousandsSeparator
    ).format(this)

    override fun toFormattedString(options: MoneyFormatterRawOptions): String = MoneyFormatter(options.toFormatterOptions()).format(this)

    override fun equals(other: Any?): Boolean = other is Money && other.centsAsLong == centsAsLong

    override fun hashCode(): Int = 31 + Currency.values.indexOf(currency) + centsAsInt

    override fun toString() = toFormattedString(abbreviate = false)
}