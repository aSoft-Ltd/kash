package kash

fun Iterable<Money>.sum(): Money {
    var total: Money = Zero
    for (i in this) total += i
    return total
}

fun <E> Iterable<E>.sumOf(summer: (E) -> Money): Money {
    var total: Money = Zero
    for (i in this) total += summer(i)
    return total
}