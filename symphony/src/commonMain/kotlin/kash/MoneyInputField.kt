@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package kash

import symphony.SingleChoiceInputField
import kotlin.js.JsExport
import kotlin.js.JsName

interface MoneyInputField : MonetaryValueInputField<Money> {
    val currency: SingleChoiceInputField<Currency>
    val amount: MonetaryInputField

    @JsName("setCurrencyValue")
    fun setCurrency(currency: Currency)

    fun setCurrency(value: String)
}