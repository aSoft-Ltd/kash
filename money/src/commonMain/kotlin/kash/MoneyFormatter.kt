@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package kash

import formatter.Formatter
import formatter.NumberFormatter
import kotlin.js.JsExport
import kotlin.jvm.JvmOverloads

class MoneyFormatter @JvmOverloads constructor(
    options: MoneyFormatterRawOptions = MoneyFormatterOptions()
) : Formatter<MonetaryValue> {
    private val amountFormatter = NumberFormatter(options)
    override fun format(o: MonetaryValue): String {
        val cur = when (o) {
            is Money -> o.currency
            else -> Currency.UXX
        }
        return amountFormatter.format(
            o.amountAsDouble
        ).replace(Template.CURRENCY_NAME, cur.name)
            .replace(Template.CURRENCY_GLOBAL_SYMBOL, cur.globalSymbol)
            .replace(Template.CURRENCY_LOCAL_SYMBOL, cur.localSymbol)
    }
}