import groovy.json.JsonSlurper
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.InputFile
import org.gradle.api.tasks.TaskAction
import java.io.File

open class CodeGenerator : DefaultTask() {

    @Input
    var packageName: String = "kash"

    @InputFile
    var input: File = project.file("src/commonMain/resources/currencies.json")

    @Input
    var className: String = "Currency"

    private val outputDir get() = project.file("src/commonMain/kotlin/${packageName.replace(".", "/")}")

    private fun generateCurrencies(): List<Map<String, String>> {
        outputDir.mkdirs()
        val output = File(outputDir, "$className.kt")
        println("Output path: ${output.absolutePath}")
        if (!input.exists()) throw Exception("input file ${input.absolutePath} does not exist")
        if (!output.exists()) output.createNewFile()

        val lines = input.readLines()
        val slurper = JsonSlurper()
        val currencies = lines.subList(1, lines.size - 1).map { json ->
            val map = slurper.parseText(json) as Map<String, String>
            mutableMapOf(*map.entries.map { it.toPair() }.toTypedArray()).apply {
                put("lowestDenomination", "100")
            }
        }
        output.writeText(
            """
            @file:Suppress("unused")
            
            package $packageName
            
            enum class $className(val symbol: String, val details: String,val lowestDenomination: UShort) {${"\n"}
        """.trimIndent()
        )

        for (entry in currencies) {
            val name = entry["name"]
            output.appendText("\t/**$name*/\n")
            output.appendText("""${"\t"}${entry["cc"]}("${symbol(entry["symbol"]!!)}","$name",${entry["lowestDenomination"]}u),${"\n\n"}""")
        }

        output.appendText("}")

        return currencies
    }

    fun generateMoney() {
        val output = File(outputDir, "Money.kt")
        if (!output.exists()) output.createNewFile()
        output.writeText(
            """
            @file:UseSerializers(LongAsStringSerializer::class)
            
            package $packageName
            
            import kotlinx.serialization.Serializable
            import kotlinx.serialization.UseSerializers
            import kotlinx.serialization.builtins.LongAsStringSerializer

            @Serializable
            data class Money(
                /** In the lowest denomination */
                val amount: ULong,
                val currency: $className
            ) {
                val readableValue get() = amount.toDouble() / currency.lowestDenomination.toDouble()
                
                val readableString get() = "${"$"}{currency.name} ${"$"}readableValue"
            }
        """.trimIndent()
        )
    }

    fun generateUtils(currencyNames: List<Map<String, String>>) {
        val output = File(outputDir, "KashUtils.kt")
        if (!output.exists()) output.createNewFile()
        output.writeText(
            """
            @file:Suppress("unused")
            
            package $packageName${"\n\n"}
        """.trimIndent()
        )
        for (curr in currencyNames) {
            val name = curr["cc"]
            val denom = curr["lowestDenomination"]
            for (type in listOf("Int", "Long", "Double", "UInt", "ULong")) {
                val convetor = when (type) {
                    "Int", "Long", "UInt" -> "toULong() * ${denom}u"
                    "ULong" -> "this * ${denom}u"
                    else -> "(toDouble() * $denom).toULong()"
                }
                output.appendText(
                    """
                    /**${curr["name"]}*/
                    inline val $type.$name get() = Money($convetor, $className.$name)${"\n"}
                """.trimIndent()
                )
            }
            output.appendText("\n")
        }
    }

    @TaskAction
    fun execute() {
        val currencies = generateCurrencies()
        generateMoney()
        generateUtils(currencies)
    }

    private fun symbol(input: String): String {
        return when {
            input == "$" -> input
            input.endsWith("$") -> input
            else -> input.replace("$", """${"$"}{"$"}""")
        }
    }
}
