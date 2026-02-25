import org.gradle.kotlin.dsl.withType
import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget
import org.jetbrains.kotlin.gradle.tasks.AbstractKotlinCompile
import org.jetbrains.kotlin.gradle.tasks.KotlinCompilationTask

plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.compose.multiplatform)
    alias(libs.plugins.android.kmp.library)
    alias(libs.plugins.kotlinx.serialization)
    alias(libs.plugins.metro)
    alias(libs.plugins.buildConfig)
    alias(libs.plugins.ksp)
}

kotlin {
    android {
        namespace = "com.davanok.dvnklamp"
        compileSdk = libs.versions.android.compileSdk.get().toInt()
        minSdk = libs.versions.android.minSdk.get().toInt()
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_17)
        }
    }
    jvm()

    js { browser() }
    @OptIn(ExperimentalWasmDsl::class)
    wasmJs { browser() }

    iosX64()
    iosArm64()
    iosSimulatorArm64()

    sourceSets {
        commonMain {
            kotlin {
                srcDir("build/generated/ksp/metadata/commonMain/kotlin")
            }
            dependencies {
                api(libs.compose.runtime)
                api(libs.compose.ui)
                api(libs.compose.foundation)
                api(libs.compose.resources)
                api(libs.compose.ui.tooling.preview)
                api(libs.compose.material3)
                implementation(libs.kermit)
                implementation(libs.kotlinx.coroutines.core)
                implementation(libs.ktor.client.core)
                implementation(libs.ktor.client.content.negotiation)
                implementation(libs.ktor.client.serialization)
                implementation(libs.ktor.serialization.json)
                implementation(libs.ktor.client.logging)
                implementation(libs.kotlinx.serialization.json)
                implementation(libs.multiplatformSettings)
                implementation(libs.kotlinx.datetime)
                implementation(libs.ktor.client.cio)
                implementation(libs.circuit.foundation)
                implementation(libs.circuit.codegen.annotations)

                implementation(project(":navigation"))
                implementation(project(":features:controlScreen"))
            }
        }

        commonTest.dependencies {
            implementation(kotlin("test"))
            implementation(libs.compose.ui.test)
            implementation(libs.kotlinx.coroutines.test)
        }

        androidMain.dependencies {
            implementation(libs.kotlinx.coroutines.android)
        }

        jvmMain.dependencies {
            implementation(compose.desktop.currentOs)
            implementation(libs.kotlinx.coroutines.swing)
        }
    }

    targets
        .withType<KotlinNativeTarget>()
        .matching { it.konanTarget.family.isAppleFamily }
        .configureEach {
            binaries {
                framework {
                    baseName = "SharedUI"
                    isStatic = true
                }
            }
        }
}
dependencies {
    add("kspCommonMainMetadata", libs.circuit.codegen)
}

buildConfig {
    // BuildConfig configuration here.
    // https://github.com/gmazzo/gradle-buildconfig-plugin#usage-in-kts
}

ksp {
    arg("circuit.codegen.mode", "metro")
}
tasks.withType<KotlinCompilationTask<*>>().configureEach {
    if (this is AbstractKotlinCompile<*>) {
        // Disable incremental in this project because we're generating top-level declarations
        // TODO remove after Soon™️ (2.2?)
        incremental = false
    }

    dependsOn("kspCommonMainKotlinMetadata")
}