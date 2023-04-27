package kash

import liquid.NumberFormatterOptions
import kash.MoneyFormatterOptions.Companion.DEFAULT_ABBREVIATE
import kash.MoneyFormatterOptions.Companion.DEFAULT_DECIMAL_SEPARATOR
import kash.MoneyFormatterOptions.Companion.DEFAULT_ENFORCE_DECIMALS
import kash.MoneyFormatterOptions.Companion.DEFAULT_POSTFIX
import kash.MoneyFormatterOptions.Companion.DEFAULT_PREFIX
import kash.MoneyFormatterOptions.Companion.DEFAULT_THOUSAND_SEPERATOR

fun MoneyFormatter(
    abbreviate: Boolean = DEFAULT_ABBREVIATE,
    prefix: String = DEFAULT_PREFIX,
    postfix: String = DEFAULT_POSTFIX,
    decimals: Int? = null,
    enforceDecimals: Boolean = DEFAULT_ENFORCE_DECIMALS,
    decimalSeparator: String = DEFAULT_DECIMAL_SEPARATOR,
    thousandsSeparator: String = DEFAULT_THOUSAND_SEPERATOR
) = MoneyFormatter(
    MoneyFormatterOptions(
        abbreviate,
        prefix,
        postfix,
        decimals = decimals ?: if (abbreviate) NumberFormatterOptions.DEFAULT_DECIMALS_ABBREVIATED else NumberFormatterOptions.DEFAULT_DECIMALS_UNABBREVIATED,
        enforceDecimals,
        decimalSeparator,
        thousandsSeparator
    )
)