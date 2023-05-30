import kommander.expect
import kash.*
import kash.serializers.MonetarySerializer
import kotlinx.serialization.json.Json
import kotlin.test.Test
import kotlin.test.assertEquals

class MonetaryTest {
    @Test
    fun should_equal() {
        val m = Monetary(45)
        println(m)
        println(Json.encodeToString(MonetarySerializer, m))
    }

    @Test
    fun should_deserialize_correctly() {
        val json = """500"""
        val money = Json.decodeFromString(MonetarySerializer, json)
        expect(money.amountAsInt).toBe(500)
        expect(money.toFormattedString()).toBe("500")
    }

    @Test
    fun should_print_usd_correctly() {
        assertEquals("3", Monetary(3.00).toFormattedString())
        assertEquals("3.15", Monetary(3.15).toFormattedString(decimals = 2))
        assertEquals("4.49", Monetary(4.49).toFormattedString(decimals = 2))
    }
}