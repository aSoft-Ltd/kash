@file:Suppress("WRONG_EXPORTED_DECLARATION")

package kash

import formatter.NumberFormatterOptions
import formatter.NumberFormatterRawOptions
import kash.MoneyFormatterOptions.Companion.DEFAULT_ABBREVIATE
import kash.MoneyFormatterOptions.Companion.DEFAULT_DECIMALS_ABBREVIATED
import kash.MoneyFormatterOptions.Companion.DEFAULT_DECIMAL_SEPARATOR
import kash.MoneyFormatterOptions.Companion.DEFAULT_ENFORCE_DECIMALS
import kash.MoneyFormatterOptions.Companion.DEFAULT_POSTFIX
import kash.MoneyFormatterOptions.Companion.DEFAULT_PREFIX
import kash.MoneyFormatterOptions.Companion.DEFAULT_THOUSAND_SEPERATOR
import kotlin.js.JsExport

@JsExport
interface MoneyFormatterRawOptions : NumberFormatterRawOptions

fun MoneyFormatterRawOptions.toFormatterOptions() = MoneyFormatterOptions(
    abbreviate = abbreviate ?: DEFAULT_ABBREVIATE,
    prefix = prefix ?: DEFAULT_PREFIX,
    postfix = postfix ?: DEFAULT_POSTFIX,
    decimals = decimals ?: DEFAULT_DECIMALS_ABBREVIATED,
    enforceDecimals = enforceDecimals ?: DEFAULT_ENFORCE_DECIMALS,
    decimalSeparator = decimalSeparator ?: DEFAULT_DECIMAL_SEPARATOR,
    thousandsSeparator = thousandsSeparator ?: DEFAULT_THOUSAND_SEPERATOR
)