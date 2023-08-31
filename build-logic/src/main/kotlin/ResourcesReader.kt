import java.io.BufferedInputStream
import java.io.InputStreamReader

object ResourcesReader {
    private fun readJsonFile(path: String): InputStreamReader {
        val stream = ResourcesReader::class.java.getResourceAsStream(path)
        return stream.reader()
    }

    fun readCurrencies() = readJsonFile("json/currencies.json")

    fun readSymbols() = readJsonFile("json/symbols.json")
}