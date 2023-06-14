@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package kash

import kotlin.js.JsExport

interface MoneyPresenter {
    val cents: Cents
    val amount: Amount
    val currency: Currency
    fun toFormattedString(): String
}