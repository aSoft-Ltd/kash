@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package kash

import formatter.NumberFormatter
import kotlin.js.JsExport
import kotlin.js.JsName

class MoneyFormatter(formatter: NumberFormatter) : NumberFormatter by formatter {

    @JsName("from")
    constructor(options: MoneyFormatterRawOptions) : this(NumberFormatter(options.toFormatterOptions()))

    fun format(o: MonetaryValue): String {
        val cur = when (o) {
            is Money -> o.currency
            else -> Currency.UXX
        }
        return format(o.amountAsDouble)
            .replace(Template.CURRENCY_NAME, cur.name)
            .replace(Template.CURRENCY_GLOBAL_SYMBOL, cur.globalSymbol)
            .replace(Template.CURRENCY_LOCAL_SYMBOL, cur.localSymbol)
    }
}