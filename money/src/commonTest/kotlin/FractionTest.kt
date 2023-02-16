import kotlin.test.Test

class FractionTest {

    data class Frac(
        val num: Long,
        val den: Long
    ) {
        constructor(number: Number) : this(number.toLong(), 1)

        companion object {
            fun gcd(num1: Long, num2: Long): Long {
                var a = num1
                var b = num2
                if (a < 0) a *= -1
                if (b < 0) b *= -1;
                var t: Long
                while (b != 0L) {
                    t = b
                    b = a % b
                    a = t
                }
                return a
            }
        }

        fun asDouble() = if (den == 0L) {
            if (num < 0) Double.NEGATIVE_INFINITY else Double.POSITIVE_INFINITY
        } else {
            num.toDouble() / den
        }

        fun reduced(): Frac {
            val gcd = gcd(num, den)
            return Frac(num / gcd, den / gcd)
        }

        operator fun plus(other: Frac): Frac = Frac(num * other.den + other.num * den, den * other.den)

        override fun toString(): String = "$num/$den"
    }

    @Test
    fun should_test_fraction_idea() {
        println("4/6 == ${Frac(4, 6).reduced()}")
        println("1/2 + 1/2 = ${(Frac(1, 2) + Frac(1, 2)).reduced()}")
    }
}