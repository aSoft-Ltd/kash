@file:Suppress("NOTHING_TO_INLINE")

package kash

import kotlin.collections.sumOf as kSumOf

inline fun Iterable<Cents>.sum(): Cents = kSumOf { it.asULong }.cents

inline fun <T> Iterable<T>.sumOf(
    selector: (T) -> Cents
): Cents = kSumOf { selector(it).asULong }.cents