@file:JsExport
@file:UseSerializers(LongAsStringSerializer::class)

package kash

import formatter.Formatter
import kash.MoneyFormatterOptions.Companion.DEFAULT_ABBREVIATE
import kash.MoneyFormatterOptions.Companion.DEFAULT_DECIMALS_ABBREVIATED
import kash.MoneyFormatterOptions.Companion.DEFAULT_DECIMALS_UNABBREVIATED
import kash.MoneyFormatterOptions.Companion.DEFAULT_DECIMAL_SEPARATOR
import kash.MoneyFormatterOptions.Companion.DEFAULT_ENFORCE_DECIMALS
import kash.MoneyFormatterOptions.Companion.DEFAULT_POSTFIX
import kash.MoneyFormatterOptions.Companion.DEFAULT_PREFIX
import kash.MoneyFormatterOptions.Companion.DEFAULT_THOUSAND_SEPERATOR
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.UseSerializers
import kotlinx.serialization.builtins.LongAsStringSerializer
import kotlin.js.JsExport
import kotlin.js.JsName
import kotlin.jvm.JvmStatic
import kotlin.jvm.JvmSynthetic

@Serializable
data class Money(
    /** In the lowest denomination */
    @SerialName("amount") val centsAsLong: ULong,
    @Serializable(with = CurrencySerializer::class)
    val currency: Currency
) {

    val centsAsInt by lazy { centsAsLong.toInt() }

    val centsAsDouble by lazy { centsAsLong.toDouble() }

    val amountAsLong by lazy { (centsAsLong.toLong() / currency.lowestDenomination) }

    val amountAsInt by lazy { (centsAsLong.toInt() / currency.lowestDenomination) }

    val amountAsDouble by lazy { (centsAsLong.toDouble() / currency.lowestDenomination) }

    companion object {
        @JvmStatic
        @JsName("fromDouble")
        fun of(amount: Double, currency: Currency) = Money((amount * currency.lowestDenomination).toULong(), currency)


        @JvmStatic
        @JvmSynthetic
        @JsName("fromInt")
        fun of(amount: Int, currency: Currency) = Money((amount.toDouble() * currency.lowestDenomination).toULong(), currency)


        @JvmStatic
        @JvmSynthetic
        @JsName("fromLong")
        fun of(amount: Long, currency: Currency) = Money((amount.toDouble() * currency.lowestDenomination).toULong(), currency)
    }

    operator fun plus(other: Money): Money {
        if (other.currency != currency) error("Can't add ${currency.name} to ${other.currency.name}")
        return Money(centsAsLong + other.centsAsLong, currency)
    }

    operator fun minus(other: Money): Money {
        if (other.currency != currency) error("Can't subtract ${currency.name} to ${other.currency.name}")
        return Money(centsAsLong - other.centsAsLong, currency)
    }

    operator fun times(quantity: Double) = Money((centsAsLong.toDouble() * quantity).toULong(), currency)

    @JsName("_ignore_times_number")
    operator fun times(quantity: Number) = times(quantity.toDouble())

    operator fun div(quantity: Double) = Money((centsAsLong.toDouble() / quantity).toULong(), currency)

    @JsName("_ignore_div")
    operator fun div(quantity: Number) = div(quantity.toDouble())

    @JsName("ratio")
    operator fun div(other: Money) = MoneyRatio((centsAsLong.toDouble() / other.centsAsLong.toDouble()), currency, other.currency)

    fun toFormattedString(): String = toFormattedString(MoneyFormatterOptions())

    fun format(formatter: Formatter<Money>) = formatter.format(this)

    @JsName("_ignore_toFormattedString")
    fun toFormattedString(
        abbreviate: Boolean = DEFAULT_ABBREVIATE,
        prefix: String = DEFAULT_PREFIX,
        postfix: String = DEFAULT_POSTFIX,
        decimals: Int? = null,
        enforceDecimals: Boolean = DEFAULT_ENFORCE_DECIMALS,
        decimalSeparator: String = DEFAULT_DECIMAL_SEPARATOR,
        thousandsSeparator: String = DEFAULT_THOUSAND_SEPERATOR
    ) = MoneyFormatter(
        abbreviate,
        prefix,
        postfix,
        decimals = decimals ?: if (abbreviate) DEFAULT_DECIMALS_ABBREVIATED else DEFAULT_DECIMALS_UNABBREVIATED,
        enforceDecimals,
        decimalSeparator,
        thousandsSeparator
    ).format(this)

    @JsName("toFormattedStringWith")
    fun toFormattedString(options: MoneyFormatterRawOptions): String = MoneyFormatter(options.toFormatterOptions()).format(this)
}