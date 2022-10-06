import groovy.json.JsonSlurper
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.InputFile
import org.gradle.api.tasks.OutputDirectory
import org.gradle.api.tasks.TaskAction
import java.io.File

open class MoneyBuildersGenerator : DefaultTask() {

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
                    inline fun $name(amount: $type) = Money.of(amount, $className.$name)${"\n"}
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
        outputDirWithPackage.mkdirs()
        val cr = CurrencyReader(currenciesInput, localSymbolsInput)
        val currencies = cr.getCurrencies()
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
