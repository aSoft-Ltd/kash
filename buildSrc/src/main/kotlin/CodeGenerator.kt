import groovy.json.JsonSlurper
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.InputFile
import org.gradle.api.tasks.OutputDirectory
import org.gradle.api.tasks.TaskAction
import java.io.File

open class CodeGenerator : DefaultTask() {

    @Input
    var packageName: String = "kash"

    @InputFile
    var currenciesInput: File = project.file("src/commonMain/resources/currencies.json")

    @InputFile
    var localSymbolsInput: File = project.file("src/commonMain/resources/symbols.json")

    @Input
    var className: String = "Currency"

    @OutputDirectory
    var outputDir = project.file("src/commonMain/kotlin")

    private val outputDirWithPackage get() = File(outputDir, packageName.replace(".", "/"))

    private fun parseJson(json: String): Map<String, String> {
        val slurper = JsonSlurper()
        return slurper.parseText(json) as Map<String, String>
    }

    private fun generateCurrencies(): List<Map<String, String>> {
        outputDirWithPackage.mkdirs()
        val output = File(outputDirWithPackage, "$className.kt")
        if (!currenciesInput.exists()) throw Exception("input file ${currenciesInput.absolutePath} does not exist")
        if (!output.exists()) output.createNewFile()

        val currencyLines = currenciesInput.readLines()
        val symbols = parseJson(localSymbolsInput.readText())

        val currencies = currencyLines.subList(1, currencyLines.size - 1).map { json ->
            val map = parseJson(json)
            mutableMapOf(*map.entries.map { it.toPair() }.toTypedArray()).apply {
                put("lowestDenomination", "100")
                put("localSymbol", symbols[map["cc"]] ?: "X")
            }
        }

        output.writeText(
            """
            /*
             * This is a generated document
             * author of the generator: https://github.com/andylamax
             */
            @file:JsExport
            @file:Suppress("unused","WRONG_EXPORTED_DECLARATION")
            
            package $packageName
            
            import kotlin.jvm.JvmSynthetic
            import kotlin.jvm.JvmStatic
            import kotlin.js.JsExport
            import kotlin.js.JsName
            import kotlinx.serialization.Serializable
            
            @Serializable(with = CurrencySerializer::class)
            sealed class $className(val name: String, val globalSymbol: String, val localSymbol: String, val details: String,val lowestDenomination: Short) {
                override fun toString() = name
                companion object {
                    @JvmStatic
                    val values by lazy { 
                        ${currencies.joinToString(separator = ", ", prefix = "arrayOf(", postfix = ")") { it["cc"].toString() }}
                    }
                    @JvmStatic
                    fun valueOf(currency: String) = values.first { it.name == currency }
                }            
        """.trimIndent()
        )


        output.appendText("\n")
        for (entry in currencies) {
            val name = entry["name"]
            output.appendText("\t/**$name*/\n")
            output.appendText("""${"\t"}object ${entry["cc"]} : $className("${entry["cc"]}","${symbol(entry["symbol"]!!)}","${symbol(entry["localSymbol"]!!)}","$name",${entry["lowestDenomination"]})""")
//            output.appendText(if (currencies.last() == entry) ";" else ",")
            output.appendText("\n\n")
        }
        output.appendText(listOf(/* "UInt", "ULong", */"Double").joinToString("\n") { type ->
            """
            |    @JsName("of${type}Value")
            |    fun of(amount: $type) = Money((amount.toDouble() * lowestDenomination).toULong(), this)
            """.trimMargin()
        })
        output.appendText("\n")
        output.appendText(listOf("Int", "Long").joinToString("\n") { type ->
            """
            |    @JvmSynthetic
            |    @JsName("of${type}Value")
            |    fun of(amount: $type) = Money((amount.toULong() * lowestDenomination.toULong()), this)
            """.trimMargin()
        })
        output.appendText("\n}")

        return currencies
    }

    private fun generateMoneyBuilder(currencyNames: List<Map<String, String>>) {
        val output = File(outputDirWithPackage, "MoneyBuilders.kt")
        if (!output.exists()) output.createNewFile()
        output.writeText(
            """
            /*
             * This is a generated document
             * author of the generator: https://github.com/andylamax
            */
            @file:JvmName("MoneyBuilders")
            @file:Suppress("unused")
            
            package $packageName${"\n"}
            
            import kotlin.jvm.JvmName${"\n\n"}
        """.trimIndent()
        )
        for (curr in currencyNames) {
            val name = curr["cc"]
            for (type in listOf("Double" /*"UInt", "ULong"*/)) {
                output.appendText(
                    """
                    /**${curr["name"]}*/
                    fun $name(amount: $type) = Money.of(amount, $className.$name)${"\n"}
                """.trimIndent()
                )
            }
            output.appendText("\n")
        }
    }

    private fun generateKashUtils(currencyNames: List<Map<String, String>>) {
        val output = File(outputDirWithPackage, "KashUtils.kt")
        if (!output.exists()) output.createNewFile()
        output.writeText(
            """
            /*
             * This is a generated document
             * author of the generator: https://github.com/andylamax
            */
            @file:Suppress("unused")
            
            package $packageName${"\n\n"}
        """.trimIndent()
        )
        for (curr in currencyNames) {
            val name = curr["cc"]
            for (type in listOf("Double", /* "UInt", "ULong", */ "Int", "Long")) {
                output.appendText(
                    """
                    /**${curr["name"]}*/
                    inline val $type.$name get() = Money.of(this, $className.$name)${"\n"}
                """.trimIndent()
                )
            }
            output.appendText("\n")
        }
    }

    @TaskAction
    fun execute() {
        val currencies = generateCurrencies()
        generateKashUtils(currencies)
        generateMoneyBuilder(currencies)
    }

    private fun symbol(input: String): String {
        return when {
            input == "$" -> input
            input.endsWith("$") -> input
            else -> input.replace("$", """${"$"}{"$"}""")
        }
    }
}
