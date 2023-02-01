package kash

fun Collection<Monetary>.sum(): Monetary {
    var total = Money(0)
    for (i in this) total += i
    return total
}

fun <E> Collection<E>.sumOf(summer: (E) -> Monetary): Monetary {
    var total = Money(0)
    for (i in this) total += summer(i)
    return total
}