@file:Suppress("NOTHING_TO_INLINE", "NON_EXPORTABLE_TYPE", "OPT_IN_USAGE")

package kash

import kash.internal.CentsULongImpl
import kotlin.js.JsExport
import kotlin.js.JsName

inline val Number.cents: Cents get() = CentsULongImpl(toLong().toULong())

inline val Int.cents: Cents get() = if (this == 0) ZeroCents else CentsULongImpl(toULong())

inline val Long.cents: Cents get() = if (this == 0L) ZeroCents else CentsULongImpl(toULong())

inline val Double.cents: Cents get() = if (this == 0.0) ZeroCents else CentsULongImpl(toULong())

inline val ULong.cents: Cents get() = if (this == 0uL) ZeroCents else CentsULongImpl(this)

@JsExport
@JsName("cents")
inline fun Cents(value: Double): Cents = value.cents