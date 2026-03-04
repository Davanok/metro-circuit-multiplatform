plugins {
    `kotlin-dsl`
}

dependencies {
    compileOnly(libs.bundles.gradle.plugins)
    compileOnly(libs.kotlin.gradle.plugin.api)
    compileOnly(libs.ksp.gradle.plugin)
    compileOnly(libs.android.gradle.plugin)
}

gradlePlugin {
    plugins {
        registerPlugin(
            id = "com.davanok.plugins.circuit-ui",
            implementationClass = "CircuitUIPlugin",
        )
        registerPlugin(
            id = "com.davanok.plugins.core",
            implementationClass = "CorePlugin"
        )
    }
}

fun NamedDomainObjectContainer<PluginDeclaration>.registerPlugin(
    id: String,
    implementationClass: String,
) {
    // For simplicity, we use id as the name of the plugin declaration
    register(id) {
        this.id = id
        this.implementationClass = implementationClass
    }
}