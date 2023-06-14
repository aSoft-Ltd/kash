package kash.internal

import kash.Cents

@PublishedApi
internal data class CentsULongImpl(override val asULong: ULong) : Cents {
    override val asLong by lazy { asULong.toLong() }
    override val asDouble by lazy { asULong.toDouble() }
}