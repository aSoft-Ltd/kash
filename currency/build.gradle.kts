import com.android.build.gradle.internal.tasks.factory.dependsOn

plugins {
    kotlin("multiplatform")
    kotlin("plugin.serialization")
    id("tz.co.asoft.library")
    id("kash-generator")
}

description = "A kotlin multiplatform library to deal with currencies"

val dir = layout.buildDirectory.dir("generated/currencies/kotlin")
val generate by tasks.registering(CurrencyGenerator::class) {
    outputDir.set(dir)
}

kotlin {
    jvm { library() }
    if (Targeting.JS) js(IR) { library() }
//    if (Targeting.WASM) wasm { library() }
    if (Targeting.OSX) osxTargets() else listOf()
//    if (Targeting.NDK) ndkTargets() else listOf()
    if (Targeting.LINUX) linuxTargets() else listOf()
    if (Targeting.MINGW) mingwTargets() else listOf()

    targets.configureEach {
        compilations.all {
            compileTaskProvider.dependsOn(generate)
        }
    }

    sourceSets {
        val commonMain by getting {
            kotlin.srcDir(dir)
            dependencies {
                api(kotlinx.serialization.core)
                api(projects.liquidNumber)
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(projects.kommanderCore)
                implementation(kotlinx.serialization.json)
            }
        }
    }
}