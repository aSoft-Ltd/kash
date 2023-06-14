@file:Suppress("NOTHING_TO_INLINE")

package kash

import kash.internal.MoneyPresenterImpl

inline fun Cents.toPresenter(
    currency: Currency,
    formatter: MoneyFormatter
): MoneyPresenter = MoneyPresenterImpl(this, currency, formatter)