import com.davanok.dvnklamp.buildlogic.libs
import com.google.devtools.ksp.gradle.KspExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.internal.Actions.with
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.withType
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension
import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget
import org.jetbrains.kotlin.gradle.tasks.KotlinCompilationTask

class CircuitUIPlugin : Plugin<Project> {
    override fun apply(target: Project)  {
        with(target) {
            val libs = libs
            /* ───────────── plugins ───────────── */

            with(pluginManager) {
                apply(libs.findPlugin("kotlin.multiplatform").get().get().pluginId)
                apply(libs.findPlugin("compose.multiplatform").get().get().pluginId)
                apply(libs.findPlugin("compose.compiler").get().get().pluginId)
                apply(libs.findPlugin("metro").get().get().pluginId)
                apply(libs.findPlugin("ksp").get().get().pluginId)
            }

            /* ───────────── kotlin ───────────── */

            extensions.configure<KotlinMultiplatformExtension> {
                sourceSets.commonMain {
                    kotlin.srcDir(
                        layout.buildDirectory.dir("generated/ksp/metadata/commonMain/kotlin")
                    )

                    dependencies {
                        api(libs.findLibrary("compose.runtime").get())
                        api(libs.findLibrary("compose.ui").get())
                        api(libs.findLibrary("compose.foundation").get())
                        api(libs.findLibrary("compose.resources").get())
                        api(libs.findLibrary("compose.material3").get())
                        api(libs.findLibrary("compose.ui.tooling.preview").get())

                        implementation(libs.findLibrary("circuit.foundation").get())
                        implementation(libs.findLibrary("circuit.codegen.annotations").get())
                    }
                }
                targets
                    .withType<KotlinNativeTarget>()
                    .matching { it.konanTarget.family.isAppleFamily }
                    .configureEach {
                        binaries.framework {
                            isStatic = true
                        }
                    }
            }

            /* ───────────── dependencies ───────────── */

            dependencies.add(
                "kspCommonMainMetadata",
                libs.findLibrary("circuit.codegen").get()
            )

            /* ───────────── ksp ───────────── */

            extensions.configure<KspExtension> {
                arg("circuit.codegen.mode", "metro")
            }

            /* ───────────── tasks ───────────── */

            tasks.withType<KotlinCompilationTask<*>>().configureEach {
                dependsOn("kspCommonMainKotlinMetadata")
            }
        }
    }
}