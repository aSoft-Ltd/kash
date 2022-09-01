@file:JsExport

package kash

import kotlin.js.JsExport

data class MoneyRatio(
    val value: Double,
    val numerator: Currency,
    val denominator: Currency
)