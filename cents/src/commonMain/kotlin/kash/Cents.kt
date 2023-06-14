@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE", "OPT_IN_USAGE")

package kash

import kash.serializer.CentsSerializer
import kotlinx.serialization.Serializable
import kotlin.js.JsExport

@Serializable(with = CentsSerializer::class)
interface Cents {
    val asULong: ULong
    val asLong: Long
    val asDouble: Double
}