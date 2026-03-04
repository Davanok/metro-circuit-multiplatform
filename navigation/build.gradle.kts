plugins {
    alias(libs.plugins.build.core)
    id("kotlin-parcelize")
}

kotlin {
    android.compilerOptions.freeCompilerArgs.addAll(
        "-P", "plugin:org.jetbrains.kotlin.parcelize:additionalAnnotation=com.davanok.dvnklamp.navigation.CommonParcelize"
    )
    sourceSets {
        commonMain.dependencies {
            implementation(libs.circuit.runtime)
        }
    }
}