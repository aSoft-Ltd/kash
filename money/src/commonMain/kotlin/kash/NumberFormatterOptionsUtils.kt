package kash

import formatter.NumberFormatterOptions

inline fun NumberFormatterOptions.toMoneyFormatterOptions() = MoneyFormatterOptions(
    abbreviate = abbreviate,
    prefix = prefix,
    postfix = postfix,
    decimals = decimals,
    enforceDecimals = enforceDecimals,
    decimalSeparator = decimalSeparator,
    thousandsSeparator = thousandsSeparator,
)