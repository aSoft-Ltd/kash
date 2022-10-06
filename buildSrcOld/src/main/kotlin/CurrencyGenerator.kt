import groovy.json.JsonSlurper
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.InputFile
import org.gradle.api.tasks.OutputDirectory
import org.gradle.api.tasks.TaskAction
import java.io.File

open class CurrencyGenerator : DefaultTask() {

    @Input
    var packageName: String = "kash"

    @InputFile
    var currenciesInput: File = project.rootProject.file("json/currencies.json")

    @InputFile
    var localSymbolsInput: File = project.rootProject.file("json/symbols.json")

    @Input
    var className: String = "Currency"

    @OutputDirectory
    var outputDir = project.file("src/commonMain/kotlin")

    private val outputDirWithPackage get() = File(outputDir, packageName.replace(".", "/"))

    private fun parseJson(json: String): Map<String, String> {
        val slurper = JsonSlurper()
        return slurper.parseText(json) as Map<String, String>
    }

    private fun generateCurrencies() {
        val output = File(outputDirWithPackage, "$className.kt")
        if (!output.exists()) output.createNewFile()
        val cr = CurrencyReader(currenciesInput, localSymbolsInput)
        val currencies = cr.getCurrencies()

        output.writeText(
            """
            /*
             * This is a generated document
             * author of the generator: https://github.com/andylamax
             */
            @file:JsExport
            @file:Suppress("unused","WRONG_EXPORTED_DECLARATION", "SERIALIZER_TYPE_INCOMPATIBLE")
            
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
                    val values : Array<$className> by lazy { 
                        ${currencies.joinToString(separator = ", ", prefix = "arrayOf(", postfix = ")") { it["cc"].toString() }}
                    }
                    @JvmStatic
                    fun valueOf(currency: String) : $className = values.first { it.name == currency }
                }            
        """.trimIndent()
        )


        output.appendText("\n")
        for (entry in currencies) {
            val name = entry["name"]
            output.appendText("\t@Serializable(with = CurrencySerializer::class)\n")
            output.appendText("\t/**$name*/\n")
            output.appendText("""${"\t"}object ${entry["cc"]} : $className("${entry["cc"]}","${symbol(entry["symbol"]!!)}","${symbol(entry["localSymbol"]!!)}","$name",${entry["lowestDenomination"]})""")
        }
        output.appendText("\n}")
    }

    @TaskAction
    fun execute() {
        outputDirWithPackage.mkdirs()
        generateCurrencies()
    }

    private fun symbol(input: String): String = when {
        input == "$" -> input
        input.endsWith("$") -> input
        else -> input.replace("$", """${"$"}{"$"}""")
    }
}
