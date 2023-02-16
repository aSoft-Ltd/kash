package kash.internal

import formatter.NumberFormatter
import formatter.NumberFormatterOptions
import formatter.NumberFormatterRawOptions
import formatter.toFormatterOptions
import kash.Currency
import kash.Monetary
import kash.Money
import kash.MoneyFormatter
import kash.MoneyFormatterOptions
import kash.MoneyRatio
import kash.Zero
import kash.exceptions.CurrencyMatchException
import kash.toMonetary

@PublishedApi
internal data class MonetaryImpl(override val centsAsLong: ULong, override val currency: Currency) : Monetary {

    override val centsAsInt = centsAsLong.toInt()

    override val centsAsDouble = centsAsLong.toDouble()

    override val amountAsLong = (centsAsLong.toLong() / currency.lowestDenomination)

    override val amountAsInt = (centsAsLong.toInt() / currency.lowestDenomination)

    override val amountAsDouble = (centsAsLong.toDouble() / currency.lowestDenomination)

    override fun with(currency: Currency) = MonetaryImpl(centsAsLong, currency)

    override fun toMonetary(): Monetary = this

    private fun currencyCheckFor(op: String, other: Money) {
        if (other.currency != currency) {
            throw CurrencyMatchException(currency, op, other.currency)
        }
    }

    override operator fun plus(other: Money): Monetary = when {
        centsAsLong == 0uL && other.centsAsLong == 0uL -> Zero
        centsAsLong == 0uL && other.centsAsLong != 0uL -> MonetaryImpl(other.centsAsLong, other.currency)
        centsAsLong != 0uL && other.centsAsLong == 0uL -> this
        else -> {
            currencyCheckFor("addition", other)
            MonetaryImpl(centsAsLong + other.centsAsLong, currency)
        }
    }

    override operator fun plus(other: Number) = plus(other.toDouble())

    override operator fun plus(other: Double): Monetary = this + other.toMonetary(currency)

    override operator fun minus(other: Money) = when {
        centsAsLong == 0uL && other.centsAsLong == 0uL -> Zero
        centsAsLong == 0uL && other.centsAsLong != 0uL -> MonetaryImpl(other.centsAsLong, other.currency)
        centsAsLong != 0uL && other.centsAsLong == 0uL -> this
        else -> {
            currencyCheckFor("subtraction", other)
            MonetaryImpl(centsAsLong + other.centsAsLong, currency)
        }
    }

    override fun minus(other: Double) = this - other.toMonetary(currency)

    override fun minus(other: Number) = minus(other.toDouble())

    override operator fun times(quantity: Double) = MonetaryImpl((centsAsDouble * quantity).toULong(), currency)

    override operator fun times(quantity: Number) = times(quantity.toDouble())

    override operator fun div(quantity: Double) = MonetaryImpl((centsAsDouble / quantity).toULong(), currency)

    override operator fun div(quantity: Number) = div(quantity.toDouble())

    override operator fun div(other: Money) = MoneyRatio(
        value = (centsAsDouble / other.centsAsDouble),
        numerator = currency,
        denominator = other.currency
    )

    override fun compareTo(other: Money): Int = when {
        centsAsLong == 0uL && other.centsAsLong == 0uL -> 0
        centsAsLong == 0uL && other.centsAsLong != 0uL -> -centsAsInt
        centsAsLong != 0uL && other.centsAsLong == 0uL -> centsAsInt
        else -> {
            currencyCheckFor("comparison", other)
            centsAsInt - other.centsAsInt
        }
    }

    override fun toFormattedString(): String = toFormattedString(MoneyFormatterOptions())

    override fun format(formatter: NumberFormatter): String = format(MoneyFormatter(formatter))

    override fun format(formatter: MoneyFormatter): String = formatter.format(this)

    override fun toFormattedString(options: NumberFormatterRawOptions): String = MoneyFormatter(
        NumberFormatter(options = options.toFormatterOptions())
    ).format(this)

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
        decimals,
        enforceDecimals,
        decimalSeparator,
        thousandsSeparator
    ).format(this)

    override fun toString() = toFormattedString(abbreviate = false)
}