@file:JsExport

package kash

import formatter.Formatter
import formatter.NumberFormatter
import kotlin.js.JsExport
import kotlin.jvm.JvmOverloads

class MoneyFormatter @JvmOverloads constructor(
    val options: MoneyFormatterRawOptions = MoneyFormatterOptions()
) : Formatter<Money> {
    private val amountFormatter = NumberFormatter(options)
    override fun format(money: Money): String {
        return amountFormatter.format(
            money.centsAsLong.toDouble() / money.currency.lowestDenomination
        ).replace(Template.CURRENCY_NAME, money.currency.name)
            .replace(Template.CURRENCY_GLOBAL_SYMBOL, money.currency.globalSymbol)
            .replace(Template.CURRENCY_LOCAL_SYMBOL, money.currency.localSymbol)
    }
}