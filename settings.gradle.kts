pluginManagement {
    includeBuild("../build-logic")
}

plugins {
    id("multimodule")
}

fun includeSubs(base: String, path: String = base, vararg subs: String) {
    subs.forEach {
        include(":$base-$it")
        project(":$base-$it").projectDir = File("$path/$it")
    }
}

listOf("kommander","kollections", "neat").forEach {
	includeBuild("../$it")
}

rootProject.name = "kash"

includeBuild("build-logic-kash")

includeSubs("kash", ".", "currency", "money", "cents")
