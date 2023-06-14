package kash

object ZeroCents : Cents {
    override val asULong = 0uL
    override val asLong = 0L
    override val asDouble = 0.0

    override fun plus(other: Cents): Cents = other

    override fun minus(other: Cents): Cents {
        if (other.asULong == 0uL) return ZeroCents
        throw RuntimeException("This operation will lead to negative cents")
    }

    override fun compareTo(other: Cents): Int = asULong.compareTo(other.asULong)

    override fun times(other: Double): Cents = ZeroCents

    override fun times(other: Int): Cents = ZeroCents

    override fun div(other: Double): Cents = ZeroCents

    override fun div(other: Int): Cents = ZeroCents
}