import groovy.json.JsonSlurper
import java.io.File

class CurrencyReader(val currenciesInput: File, val localSymbolsInput: File) {

    private fun parseJson(json: String): Map<String, String> {
        val slurper = JsonSlurper()
        return slurper.parseText(json) as Map<String, String>
    }

    fun getCurrencies(): List<Map<String, String>> {
        if (!currenciesInput.exists()) throw Exception("input file ${currenciesInput.absolutePath} does not exist")

        val currencyLines = currenciesInput.readLines()
        val symbols = parseJson(localSymbolsInput.readText())

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