package kash.internal

import kash.Amount
import kash.Cents
import kash.Currency
import kash.MoneyFormatter
import kash.MoneyPresenter

@PublishedApi
internal class MoneyPresenterImpl(
    override val cents: Cents,
    override val currency: Currency,
    val formatter: MoneyFormatter
) : MoneyPresenter {
    override val amount: Amount by lazy { cents / 100 }

    private val money = MonetaryImpl(cents.asULong, currency)

    override fun toFormattedString(): String = money.format(formatter)
}