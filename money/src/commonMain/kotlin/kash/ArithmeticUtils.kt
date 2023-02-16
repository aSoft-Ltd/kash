package kash

fun Collection<Monetary>.total(): Monetary {
    var total = Money(0)
    for (i in this) total += i
    return total
}

fun <E> Collection<E>.totalOf(summer: (E) -> Monetary): Monetary {
    var total = Money(0)
    for (i in this) total += summer(i)
    return total
}

fun Collection<Money>.sum(): Money {
    var total = Zero
    for (i in this) total += i
    return total
}

fun <E> Collection<E>.sumOf(summer: (E) -> Money): Money {
    var total = Zero
    for (i in this) total += summer(i)
    return total
}