import kotlinx.serialization.json.Json
import kash.Money
import kash.TZS
import kash.UGX
import kash.USD
import kotlin.test.Test
import kotlin.test.assertEquals

class MoneyTest {
    @Test
    fun should_equal() {
        val m = 45.TZS
        println(m)

        println(Json.encodeToString(Money.serializer(), m))
    }

    @Test
    fun should_deserialize_correctly() {
        val json = """{"amount":50000,"currency":"TZS"}"""
        val money = Json.decodeFromString(Money.serializer(), json)
        assertEquals(money, 500.TZS)
        assertEquals(money.readableString, "TZS 500.0")
    }

    @Test
    fun should_print_usd_correctly() {
        assertEquals(3.00.USD.readableString, "USD 3.0")
        assertEquals(3.15.USD.readableString, "USD 3.15")
        assertEquals(4.49.UGX.readableString, "UGX 4.49")
    }
}