import kommander.expect
import kash.TZS
import kash.Zero
import kash.sum
import kotlin.test.Test

class MoneyArithmeticsTest {
    @Test
    fun should_any_currency_to_zero() {
        expect(4000.TZS + Zero).toBe(4000.TZS)
    }

    @Test
    fun should_add_zero_to_any_currency() {
        expect(Zero + 4000.TZS).toBe(4000.TZS)
    }

    @Test
    fun should_be_able_to_get_total_of_money() {
        val cash = listOf(1000.TZS, 2000.TZS, 3000.TZS)
        expect(cash.sum()).toBe(6000.TZS)
    }

    @Test
    fun should_be_able_to_get_total_of_zeros() {
        val cash = List(3) { Zero }
        expect(cash.sum()).toBe(Zero)
    }
}