@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package kash

import kotlin.js.JsExport
import kotlin.js.JsName

interface Arithmetic<T> : Comparable<T> {
    operator fun plus(other: T): T

    operator fun minus(other: T): T

    operator fun times(quantity: Double): T

    @JsName("_ignore_times_number")
    operator fun times(quantity: Number): T

    operator fun div(quantity: Double): T

    @JsName("_ignore_div")
    operator fun div(quantity: Number): T

    override fun compareTo(other: T): Int
}