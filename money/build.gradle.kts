import com.android.build.gradle.internal.tasks.factory.dependsOn

plugins {
    kotlin("multiplatform")
    kotlin("plugin.serialization")
    id("tz.co.asoft.library")
    id("kash-generator")
    signing
}

val dir = layout.buildDirectory.dir("generated/builders/kotlin")
val generate by tasks.registering(MoneyBuildersGenerator::class) {
    outputDir.set(dir)
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
            compileTaskProvider.dependsOn(generate)
        }
    }

    sourceSets {
        val commonMain by getting {
            kotlin.srcDir(dir)
            dependencies {
                api(projects.kashCurrency)
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(projects.expectCore)
                implementation(kotlinx.serialization.json)
            }
        }
    }
}

aSoftOSSLibrary(
    version = asoft.versions.root.get(),
    description = "A kotlin multiplatform library to deal with money"
)