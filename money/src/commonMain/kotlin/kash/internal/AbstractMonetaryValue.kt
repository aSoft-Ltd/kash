package kash.internal

import formatter.Formatter
import kash.Currency
import kash.MonetaryValue

internal abstract class AbstractMonetaryValue(
    final override val centsAsLong: ULong,
    currency: Currency
) : MonetaryValue {
    override val centsAsInt = centsAsLong.toInt()

    override val centsAsDouble = centsAsLong.toDouble()

    override val amountAsLong = (centsAsLong.toLong() / currency.lowestDenomination)

    override val amountAsInt = (centsAsLong.toInt() / currency.lowestDenomination)

    override val amountAsDouble = (centsAsLong.toDouble() / currency.lowestDenomination)

    override fun formatValue(formatter: Formatter<MonetaryValue>) = formatter.format(this)

    override fun with(currency: Currency) = MoneyImpl(centsAsLong, currency)
}