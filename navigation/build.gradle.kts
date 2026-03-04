plugins {
    alias(libs.plugins.build.core)
    id("kotlin-parcelize")
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(libs.circuit.runtime)
        }
    }
}