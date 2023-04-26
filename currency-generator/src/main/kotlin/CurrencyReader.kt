import groovy.json.JsonSlurper
import java.io.File

class CurrencyReader {

    private fun parseJson(json: String): Map<String, String> {
        val slurper = JsonSlurper()
        return slurper.parseText(json) as Map<String, String>
    }

    fun getCurrencies(): List<Map<String, String>> {
        val currencyLines = ResourcesReader.readCurrencies().readLines()
        val symbols = parseJson(ResourcesReader.readSymbols().readText())

        val currencies = currencyLines.subList(1, currencyLines.size - 1).map { json ->
            val map = parseJson(json)
            mutableMapOf(*map.entries.map { it.toPair() }.toTypedArray()).apply {
                put("lowestDenomination", "100")
                put("localSymbol", symbols[map["cc"]] ?: "X")
            }
        }
        return currencies
    }
}