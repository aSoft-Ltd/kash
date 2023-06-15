package kash.internal

import kash.Cents

@PublishedApi
internal data class CentsULongImpl(override val asULong: ULong) : Cents {
    override val asLong by lazy { asULong.toLong() }
    override val asDouble by lazy { asULong.toDouble() }

    override fun plus(other: Cents): Cents = CentsULongImpl(asULong + other.asULong)

    override fun minus(other: Cents): Cents = CentsULongImpl(asULong - other.asULong)

    override fun times(other: Double): Cents = CentsULongImpl((other * asULong.toDouble()).toULong())

    override fun times(other: Int): Cents = CentsULongImpl(other.toULong() * asULong)

    override fun div(other: Double): Cents = CentsULongImpl((asULong.toDouble() / other).toULong())

    override fun div(other: Int): Cents = CentsULongImpl((asULong / other.toULong()))

    override fun compareTo(other: Cents): Int = asULong.compareTo(other.asULong)

    override fun toString(): String = "$asULong cents"
}