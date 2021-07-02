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
        println(500.TZS.readableString)
        assertEquals("TZS 500.0", money.readableString)
    }

    @Test
    fun should_print_usd_correctly() {
        assertEquals("USD 3.0", 3.00.USD.readableString)
        assertEquals("USD 3.15", 3.15.USD.readableString)
        assertEquals("UGX 4.49", 4.49.UGX.readableString)
    }
}