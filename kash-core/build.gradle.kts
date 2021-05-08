plugins {
    kotlin("multiplatform")
    kotlin("plugin.serialization")
    id("tz.co.asoft.library")
    id("io.codearte.nexus-staging")
    id("dev.zacsweers.kgp-150-leak-patcher") version "1.0.1"
    signing
}

kotlin {
    jvm { library() }
    js(IR) { library() }
    macosX64 { }
    ios()
    tvos()
    watchos()
    linuxArm64()
    linuxArm32Hfp()
    linuxX64()

    sourceSets {
        val commonMain by getting {
            dependencies {
                api(kotlinx("serialization-core", vers.kotlinx.serialization))
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
                implementation(kotlinx("serialization-json", vers.kotlinx.serialization))
            }
        }
    }
}

val generate by tasks.creating(CodeGenerator::class)

val assemble by tasks.getting {
    dependsOn(generate)
}

aSoftOSSLibrary(
    version = vers.asoft.kash,
    description = "A kotlin multiplatform library to deal with money and currencies"
)