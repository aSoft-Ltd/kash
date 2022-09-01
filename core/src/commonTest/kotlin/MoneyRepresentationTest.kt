import expect.expect
import kash.GBP
import kash.MXN
import kash.Template
import kash.TZS
import kotlin.test.Test

class MoneyRepresentationTest {

    @Test
    fun should_easily_abbreviate_thousands() {
        expect(5000.TZS.toFormattedString()).toBe("TZS 5K")
    }

    @Test
    fun should_easily_abbreviate_hundreds_of_thousands() {
        expect(450_000.TZS.toFormattedString()).toBe("TZS 450K")
    }

    @Test
    fun should_easily_abbreviate_thousands_in_mexican_peso() {
        expect(3000.MXN.toFormattedString()).toBe("$ 3K")
        expect(4200.MXN.toFormattedString(prefix = "${Template.CURRENCY_NAME} ")).toBe("MXN 4.2K")
        expect(4231.MXN.toFormattedString(prefix = "${Template.CURRENCY_NAME} ", decimals = 1)).toBe("MXN 4.2K")
        expect(4231.MXN.toFormattedString(prefix = "${Template.CURRENCY_NAME} ", decimals = 0)).toBe("MXN 4K")
        expect(4231.MXN.toFormattedString(prefix = "${Template.CURRENCY_NAME} ")).toBe("MXN 4.2K")
    }

    @Test
    fun should_easily_millions() {
        expect(2_000_000.MXN.toFormattedString()).toBe("$ 2M")
    }

    @Test
    fun should_easily_abbreviate_decimals_too() {
        expect(2_160_000.MXN.toFormattedString()).toBe("$ 2.2M")
        expect(2_160_000.MXN.toFormattedString(decimals = 2)).toBe("$ 2.16M")
    }

    @Test
    fun should_easily_display_commas_in_thousands() {
        expect(4500.TZS.toFormattedString(abbreviate = false)).toBe("TZS 4,500")
    }

    @Test
    fun should_easily_display_commas_with_decimals_too() {
        expect(6700.05.TZS.toFormattedString(abbreviate = false)).toBe("TZS 6,700.05")
        expect(6700.09.TZS.toFormattedString(abbreviate = false, decimals = 1)).toBe("TZS 6,700.1")
        expect(6700.06.TZS.toFormattedString(abbreviate = false, decimals = 1)).toBe("TZS 6,700.1")
    }

    @Test
    fun should_print_tanzanian_values() {
        expect(3000.GBP.toFormattedString()).toBe("£ 3K")
    }
}