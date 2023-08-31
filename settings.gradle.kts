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

includeBuild("../kommander")
includeBuild("build-logic")

rootProject.name = "kash"

includeSubs("kash", ".", "currency", "money", "cents")
