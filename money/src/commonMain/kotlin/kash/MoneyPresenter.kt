@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package kash

import kash.internal.MonetaryImpl
import kotlinx.serialization.Transient
import kotlin.js.JsExport

data class MoneyPresenter(
    val cents: Cents,
    val currency: Currency,
    val formatter: MoneyFormatter
) {
    val amount: Amount by lazy { cents / 100 }

    @Transient
    val money: Monetary = MonetaryImpl(cents.asULong, currency)

    fun toFormattedString(): String = money.format(formatter)
}