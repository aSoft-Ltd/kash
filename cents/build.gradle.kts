import com.android.build.gradle.internal.tasks.factory.dependsOn

plugins {
    kotlin("multiplatform")
    kotlin("plugin.serialization")
    id("tz.co.asoft.library")
}

description = "A kotlin multiplatform library to deal with money"


kotlin {
    jvm { library() }
    if (Targeting.JS) js(IR) { library() }
//    if (Targeting.WASM) wasm { library() }
    if (Targeting.OSX) osxTargets() else listOf()
//    if (Targeting.NDK) ndkTargets() else listOf()
    if (Targeting.LINUX) linuxTargets() else listOf()
    if (Targeting.MINGW) mingwTargets() else listOf()

    sourceSets {
        val commonMain by getting {
			dependencies {
				api(kotlinx.serialization.core)
			}
        }
        val commonTest by getting {
            dependencies {
                implementation(libs.kommander.core)
                implementation(kotlinx.serialization.json)
            }
        }
    }
}
