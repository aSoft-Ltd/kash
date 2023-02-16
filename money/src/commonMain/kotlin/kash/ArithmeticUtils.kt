package kash

fun Collection<Money>.sum(): Money {
    var total: Money = Zero
    for (i in this) total += i
    return total
}

fun <E> Collection<E>.sumOf(summer: (E) -> Money): Money {
    var total: Money = Zero
    for (i in this) total += summer(i)
    return total
}