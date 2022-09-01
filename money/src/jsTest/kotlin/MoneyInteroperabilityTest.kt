import expect.expect
import kash.TZS
import kotlin.test.Test

class MoneyInteroperabilityTest {
    private inline fun <T> jso(builder: T.() -> Unit): T = (js("{}") as T).apply(builder)

    @Test
    fun should_be_callable_from_javascript() {
        val output: String = 1000.TZS.asDynamic().toFormattedString(jso { abbreviate = true })
        expect(output).toBe("TZS 1K")
    }

    @Test
    fun should_be_callable_from_javascript_with_abbreviated_set_to_false() {
        val output: String = 1000.TZS.asDynamic().toFormattedStringWith(jso { abbreviate = false })
        expect(output).toBe("TZS 1,000")
    }

    @Test
    fun should_be_callable_without_argument_whatsoever() {
        val output: String = 4500.TZS.asDynamic().toFormattedString()
        expect(output).toBe("TZS 4.5K")
    }
}