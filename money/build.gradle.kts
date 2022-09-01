plugins {
    kotlin("multiplatform")
    kotlin("plugin.serialization")
    id("tz.co.asoft.library")
    signing
}

val generate by tasks.creating(MoneyBuildersGenerator::class) {
    outputDir = file("build/generated/builders/kotlin")
}

kotlin {
    jvm {
        library();
        withJava();
    }
    js(IR) { library() }

    linuxTargets(true)

    targets.configureEach {
        compilations.all {
            compileKotlinTask.dependsOn("generate")
        }
    }

    sourceSets {
        val commonMain by getting {
            kotlin.srcDir(generate.outputDir)
            dependencies {
                api(projects.kashCurrency)
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(asoft.expect.core)
                implementation(kotlinx.serialization.json)
            }
        }
    }
}

aSoftOSSLibrary(
    version = asoft.versions.root.get(),
    description = "A kotlin multiplatform library to deal with money"
)