package kash

fun Iterable<Money>.sum(): Money {
    var total: Money = Zero
    for (i in this) total += i
    return total
}