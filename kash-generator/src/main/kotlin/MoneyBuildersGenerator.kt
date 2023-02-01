import groovy.json.JsonSlurper
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.InputFile
import org.gradle.api.tasks.OutputDirectory
import org.gradle.api.tasks.TaskAction
import java.io.File

abstract class MoneyBuildersGenerator : AbstractGenerator() {

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
            
            package $pkg${"\n"}
            
            import kotlin.jvm.JvmName${"\n\n"}
        """.trimIndent()
        )
        for (curr in currencyNames) {
            val name = curr["cc"]
            for (type in listOf("Double" /*"UInt", "ULong"*/)) {
                output.appendText(
                    """
                    /**${curr["name"]}*/
                    inline fun $name(amount: $type) = Money(amount, $clazz.$name)${"\n"}
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
            
            package $pkg${"\n\n"}
        """.trimIndent()
        )
        for (curr in currencyNames) {
            val name = curr["cc"]
            for (type in listOf("Double", /* "UInt", "ULong", */ "Int", "Long")) {
                output.appendText(
                    """
                    /**${curr["name"]}*/
                    inline val $type.$name get() = Money(this, $clazz.$name)${"\n"}
                """.trimIndent()
                )
            }
            output.appendText("\n")
        }
    }

    @TaskAction
    fun execute() {
        outputDirWithPackage.mkdirs()
        val cr = CurrencyReader()
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
