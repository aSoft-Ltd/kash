@file:Suppress("NOTHING_TO_INLINE")

package kash.transformers

import kash.Cents
import kash.Currency
import kash.MoneyFormatter
import kash.MoneyPresenter

inline fun Cents.toPresenter(
    currency: Currency,
    formatter: MoneyFormatter
): MoneyPresenter = MoneyPresenter(this, currency, formatter)