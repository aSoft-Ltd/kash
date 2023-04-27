package kash

import kommander.expect
import koncurrent.Later
import kotlinx.serialization.Serializable
import symphony.Fields
import symphony.Form
import symphony.FormActionsBuildingBlock
import symphony.FormConfig
import symphony.name
import kotlin.test.Test

class FormWithMoneyInputFieldTest {
    @Serializable
    data class TestParams(val name: String, val price: Money)
    class TestFields(currency: Currency?) : Fields() {
        val name = name()
        val price = money(
            name = TestParams::price,
            currency = currency
        )
    }

    class TestForm(
        curreny: Currency?,
        init: FormActionsBuildingBlock<TestParams, TestParams>
    ) : Form<TestFields, TestParams, TestParams>(
        heading = "Test Form",
        details = "This is a test form",
        fields = TestFields(curreny),
        config = FormConfig(),
        initializer = init
    )

    @Test
    fun should_submit_from_a_form_with_a_null_currency_value() {
        var params: TestParams? = null
        val form = TestForm(null) {
            onCancel { println("Cancelled") }
            onSubmit {
                params = it
                Later(it)
            }
        }

        with(form.fields) {
            name.type("Lamax")
            price.setCurrency("TZS")
            price.setAmount(200)
            expect(price.data.value.output?.amountAsDouble).toBe(200.0)
        }
        form.submit()

        expect(params?.price?.amountAsDouble).toBe(200.0)
    }

    @Test
    fun should_submit_from_a_form_with_a_non_null_currency_value() {
        var params: TestParams? = null
        val form = TestForm(Currency.TZS) {
            onCancel { println("Cancelled") }
            onSubmit {
                params = it
                Later(it)
            }
        }

        with(form.fields) {
            name.type("Lamax")
            price.setAmount(200)
        }
        form.submit()

        expect(params?.price?.amountAsDouble).toBe(200.0)
    }
}