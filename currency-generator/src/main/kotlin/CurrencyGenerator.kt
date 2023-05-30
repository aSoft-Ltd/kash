import GeneratorDefaults.DEFAULT_PACKAGE_NAME
import groovy.json.JsonSlurper
import org.gradle.api.tasks.TaskAction
import java.io.File

abstract class CurrencyGenerator : AbstractGenerator() {

    private fun parseJson(json: String): Map<String, String> {
        val slurper = JsonSlurper()
        return slurper.parseText(json) as Map<String, String>
    }

    private fun generateCurrencies() {
        val output = File(outputDirWithPackage, "$clazz.kt")
        if (!output.exists()) output.createNewFile()
        val cr = CurrencyReader()
        val currencies = cr.getCurrencies()

        output.writeText(
            """
            /*
             * This is a generated document
             * author of the generator: https://github.com/andylamax
             */
            @file:JsExport
            @file:Suppress("unused","WRONG_EXPORTED_DECLARATION", "SERIALIZER_TYPE_INCOMPATIBLE")
            
            package $pkg
            
            import kotlin.jvm.JvmStatic
            import kotlin.js.JsExport
            import kotlinx.serialization.Serializable
            
            @Serializable(with = ISO3CurrencySerializer::class)
            sealed class $clazz(val name: String, val globalSymbol: String, val localSymbol: String, val details: String,val lowestDenomination: Short) {
                override fun toString() = name
                companion object {
                    @JvmStatic
                    val values : Array<$clazz> by lazy { 
                        ${currencies.joinToString(separator = ", ", prefix = "arrayOf(", postfix = ")") { it["cc"].toString() }}
                    }
                    @JvmStatic
                    fun valueOf(currency: String) : $clazz = values.first { it.name == currency }
                    
                    @JvmStatic
                    fun valueOfOrNull(currency: String?) : $clazz? = values.firstOrNull { it.name == currency }
                    
                    @JvmStatic
                    fun valueOfOrDefault(currency: String?, default: $clazz) : $clazz = values.firstOrNull { it.name == currency } ?: default
                }            
        """.trimIndent()
        )


        output.appendText("\n")
        for (entry in currencies) {
            val name = entry["name"]
            output.appendText("\n\t@Serializable(with = ISO3CurrencySerializer::class)\n")
            output.appendText("\t/**$name*/\n")
            output.appendText("""${"\t"}object ${entry["cc"]} : $clazz("${entry["cc"]}","${symbol(entry["symbol"]!!)}","${symbol(entry["localSymbol"]!!)}","$name",${entry["lowestDenomination"]})""")
            output.appendText("\n")
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