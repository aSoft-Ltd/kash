package kash

import kommander.expect
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlin.test.Test

class CentsSerializerTest {

    @Test
    fun should_serialize_cents() {
        val cents = 100.cents
        expect(Json.encodeToString(cents)).toBe("100")
    }

    @Test
    fun should_deserialize_cents() {
        expect(Json.decodeFromString<Cents>("100")).toBe(100.cents)
    }

    @Test
    fun should_serialize_maximum_ulong_values() {
        val cents = ULong.MAX_VALUE.cents
        expect(Json.encodeToString(cents)).toBe("${ULong.MAX_VALUE}")
    }

    @Test
    fun should_deserialize_maximum_ulong_values() {
        val cents = ULong.MAX_VALUE.cents
        expect(Json.decodeFromString<Cents>("${ULong.MAX_VALUE}")).toBe(cents)
    }
}