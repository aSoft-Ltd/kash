package kash.internal

import liquid.NumberFormatter
import liquid.NumberFormatterRawOptions
import liquid.toFormatterOptions
import kash.MoneyFormatter
import kash.Pretty

abstract class AbstractPretty : Pretty {

    override fun toFormattedString(): String = format(MoneyFormatter())

    override fun format(formatter: NumberFormatter): String = format(MoneyFormatter(formatter))

    abstract override fun format(formatter: MoneyFormatter): String

    override fun toFormattedStringWith(options: NumberFormatterRawOptions): String = format(
        MoneyFormatter(NumberFormatter(options = options.toFormatterOptions()))
    )

    override fun toFormattedString(
        abbreviate: Boolean,
        prefix: String,
        postfix: String,
        decimals: Int?,
        enforceDecimals: Boolean,
        decimalSeparator: String,
        thousandsSeparator: String
    ) = format(
        MoneyFormatter(abbreviate, prefix, postfix, decimals, enforceDecimals, decimalSeparator, thousandsSeparator)
    )
}