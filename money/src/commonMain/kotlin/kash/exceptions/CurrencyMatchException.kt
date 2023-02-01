package kash.exceptions

import kash.Currency

class CurrencyMatchException(val lhs: Currency, val op: String, val rhs: Currency) : RuntimeException(
    "Can not perform $op operation between ${lhs.name} and ${rhs.name}"
)