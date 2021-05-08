import kash.Currency
import kotlin.test.Test

class CurrencySymbolTest {
    @Test
    fun should_print_symbols_correctly() {
        val usd = Currency.USD
        println(usd.symbol)
    }

    @Test
    fun should_print_uruguay_pesso_correctly() {
        val uruguay = Currency.UYU
        println(uruguay.symbol)
    }

    @Test
    fun should_print_nedherlands_symbol() {
        println(Currency.ANG.symbol)
    }

    @Test
    fun should_print_unknown_symbols() {
        println(Currency.AED.symbol)
    }

    @Test
    fun prints_proper_denomination() {
        println(Currency.TZS.lowestDenomination)
    }
}
