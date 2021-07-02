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
            
            import kotlin.jvm.JvmSynthetic
            
            enum class $className(val symbol: String, val details: String,val lowestDenomination: UShort) {${"\n"}
        """.trimIndent()
        )

        output.appendText("\n")
        for (entry in currencies) {
            val name = entry["name"]
            output.appendText("\t/**$name*/\n")
            output.appendText("""${"\t"}${entry["cc"]}("${symbol(entry["symbol"]!!)}","$name",${entry["lowestDenomination"]}u)""")
            output.appendText(if (currencies.last() == entry) ";" else ",")
            output.appendText("\n\n")
        }
        output.appendText(listOf("UInt", "ULong", "Double").joinToString("\n") { type ->
            val multiplier = "lowestDenomination${if (type.startsWith("U")) "" else ".toShort()"}"
            """
            |    fun of(amount: $type) = Money((amount * $multiplier).toULong(), this)
            """.trimMargin()
        })
        output.appendText("\n")
        output.appendText(listOf("Int", "Long").joinToString("\n") { type ->
            val multiplier = "lowestDenomination${if (type.startsWith("U")) "" else ".toShort()"}"
            """
            |    @JvmSynthetic
            |    fun of(amount: $type) = Money((amount * $multiplier).toULong(), this)
            """.trimMargin()
        })
        output.appendText("\n}")

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
            import kotlin.jvm.JvmStatic
            import kotlin.jvm.JvmSynthetic
            import kotlin.math.floor

            @Serializable
            data class Money internal constructor(
                /** In the lowest denomination */
                val amount: ULong,
                val currency: $className
            ) {
               
                companion object {
            ${
                listOf("UInt", "ULong", "Double").joinToString("\n") { type ->
                    val multiplier = "currency.lowestDenomination${if (type.startsWith("U")) "" else ".toShort()"}"
                    """
                    @JvmStatic
                    fun of(amount: $type, currency: $className) = Money((amount * $multiplier).toULong(), currency)
                   """
                }
            }
            
             ${
                listOf("Int", "Long").joinToString("\n") { type ->
                    val multiplier = "currency.lowestDenomination${if (type.startsWith("U")) "" else ".toShort()"}"
                    """
                    @JvmStatic
                    @JvmSynthetic
                    fun of(amount: $type, currency: $className) = Money((amount * $multiplier).toULong(), currency)
                   """
                }
            }
                }
                val readableValue get() = amount.toDouble() / currency.lowestDenomination.toDouble()
                
                   val readableString
        get() = (currency.name + " " + if (readableValue - floor(readableValue) == 0.0) "${"$"}readableValue.0" else readableValue).replace(
            ".0.0",
            ".0"
        )
            }
        """.trimIndent()
        )
    }

    fun generateMoneyBuilder(currencyNames: List<Map<String, String>>) {
        val output = File(outputDir, "MoneyBuilders.kt")
        if (!output.exists()) output.createNewFile()
        output.writeText(
            """
            @file:JvmName("MoneyBuilders")
            @file:Suppress("unused")
            
            package $packageName${"\n"}
            
            import kotlin.jvm.JvmName${"\n\n"}
        """.trimIndent()
        )
        for (curr in currencyNames) {
            val name = curr["cc"]
            for (type in listOf("Double", "UInt", "ULong")) {
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

    fun generateKashUtils(currencyNames: List<Map<String, String>>) {
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
            for (type in listOf("Double", "UInt", "ULong", "Int", "Long")) {
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
        generateMoney()
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
