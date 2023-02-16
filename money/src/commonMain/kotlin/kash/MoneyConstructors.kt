@file:JvmName("Cash")
@file:Suppress("NON_EXPORTABLE_TYPE")

package kash

import kash.internal.MonetaryImpl
import kash.internal.MoneyImpl
import kotlin.js.JsExport
import kotlin.js.JsName
import kotlin.jvm.JvmName

@PublishedApi
internal const val MONETARY_LOWEST_DENOMINATION = 100

val Zero: Money = MoneyImpl(0u, Currency.UXX)

inline fun Money(cents: ULong, currency: Currency): Money = MoneyImpl(cents, currency)

@JsExport
@JsName("moneyOf")
@JvmName("of")
inline fun Money(amount: Double, currency: Currency): Money =
    MoneyImpl((amount * currency.lowestDenomination).toULong(), currency)

@JsName("monetaryOf")
@JvmName("of")
inline fun Money(amount: Double): Monetary = MonetaryImpl((amount * MONETARY_LOWEST_DENOMINATION).toULong())

@JvmName("of")
inline fun Money(amount: Int, currency: Currency): Money =
    MoneyImpl((amount.toDouble() * currency.lowestDenomination).toULong(), currency)

@JvmName("of")
inline fun Money(amount: Int): Monetary = MonetaryImpl((amount.toDouble() * MONETARY_LOWEST_DENOMINATION).toULong())

@JvmName("of")
inline fun Money(amount: Long, currency: Currency): Money =
    MoneyImpl((amount.toDouble() * currency.lowestDenomination).toULong(), currency)

@JvmName("of")
inline fun Money(amount: Long): Monetary = MonetaryImpl((amount.toDouble() * MONETARY_LOWEST_DENOMINATION).toULong())