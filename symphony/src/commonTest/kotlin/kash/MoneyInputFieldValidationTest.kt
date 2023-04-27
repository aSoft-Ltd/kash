package kash

import kommander.expect
import kommander.toBe
import symphony.validation.Valid
import kotlin.test.Test

class MoneyInputFieldValidationTest {
    @Test
    fun can_validate_capture_money_without_problem() {
        val money = MoneyInputField("price")
        money.currency.selectItem(Currency.TZS)
        money.amount.set("200")

        val res = money.validate()
        expect(res).toBe<Valid>()

        expect(money.data.value.output).toBe(200.TZS)
    }
}