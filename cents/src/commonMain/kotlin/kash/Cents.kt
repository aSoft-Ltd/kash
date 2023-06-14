@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE", "OPT_IN_USAGE")

package kash

import kash.serializer.CentsSerializer
import kotlinx.serialization.Serializable
import kotlin.js.JsExport
import kotlin.js.JsName

@Serializable(with = CentsSerializer::class)
interface Cents : Comparable<Cents> {
    val asULong: ULong
    val asLong: Long
    val asDouble: Double

    operator fun plus(other: Cents): Cents
    operator fun minus(other: Cents): Cents
    operator fun times(other: Double): Cents

    @JsName("_ignore_timesInt")
    operator fun times(other: Int): Cents
    operator fun div(other: Double): Cents

    @JsName("_ignore_divInt")
    operator fun div(other: Int): Cents
}