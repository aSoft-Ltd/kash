plugins {
    kotlin("multiplatform")
    kotlin("plugin.serialization")
    id("tz.co.asoft.library")
    id("io.codearte.nexus-staging")
    signing
}

kotlin {
    jvm {
        library();
        withJava();
    }
    js(IR) { library() }
    val darwinTargets = listOf(
        macosX64(),
        iosArm64(),
        iosArm32(),// comment this out if IDEA can't resolve source sets
        iosX64(),
        watchosArm32(),
        watchosArm64(),
        watchosX86(), // comment this out if IDEA can't resolve source sets
        tvosArm64(),
        tvosX64()
    )

    val linuxTargets = listOf(
        linuxX64()
    )

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