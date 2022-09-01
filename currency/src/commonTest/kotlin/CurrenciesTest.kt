import kash.Currency
import kotlin.test.Test

class CurrencySymbolTest {
    @Test
    fun should_print_symbols_correctly() {
        val usd = Currency.USD
        println(usd.globalSymbol)
    }

    @Test
    fun should_print_uruguay_pesso_correctly() {
        val uruguay = Currency.UYU
        println(uruguay.globalSymbol)
        println(uruguay.localSymbol)
    }

    @Test
    fun should_print_nedherlands_symbol() {
        println(Currency.ANG.globalSymbol)
    }

    @Test
    fun should_print_unknown_symbols() {
        println(Currency.AED.globalSymbol)
    }

    @Test
    fun prints_proper_denomination() {
        println(Currency.TZS.lowestDenomination)
    }
}
