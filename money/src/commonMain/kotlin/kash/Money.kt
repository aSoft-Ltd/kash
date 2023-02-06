@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package kash

import kash.MoneyFormatterOptions.Companion.DEFAULT_ABBREVIATE
import kash.MoneyFormatterOptions.Companion.DEFAULT_DECIMAL_SEPARATOR
import kash.MoneyFormatterOptions.Companion.DEFAULT_ENFORCE_DECIMALS
import kash.MoneyFormatterOptions.Companion.DEFAULT_POSTFIX
import kash.MoneyFormatterOptions.Companion.DEFAULT_PREFIX
import kash.MoneyFormatterOptions.Companion.DEFAULT_THOUSAND_SEPERATOR
import kash.serializers.MoneySerializer
import kotlinx.serialization.Serializable
import kotlin.js.JsExport
import kotlin.js.JsName

@Serializable(with = MoneySerializer::class)
interface Money : MonetaryValue, Arithmetic<Money> {
    val currency: Currency

    fun toMonetary(): Monetary

    @JsName("ratio")
    operator fun div(other: Money): MoneyRatio

    override fun compareTo(other: Money): Int

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
}