import com.android.build.api.dsl.androidLibrary
import com.davanok.dvnklamp.buildlogic.defaultNamespace
import com.davanok.dvnklamp.buildlogic.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.internal.Actions.with
import org.gradle.kotlin.dsl.configure
import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

class CorePlugin : Plugin<Project> {
    override fun apply(target: Project)  {
        with(target) {
            val libs = libs

            /* ───────────── plugins ───────────── */

            with(pluginManager) {
                apply(libs.findPlugin("kotlin.multiplatform").get().get().pluginId)
                apply(libs.findPlugin("android.kmp.library").get().get().pluginId)
            }

            /* ───────────── kotlin ───────────── */

            extensions.configure<KotlinMultiplatformExtension> {
                jvm()
                js { browser() }
                @OptIn(ExperimentalWasmDsl::class)
                wasmJs { browser() }
                iosX64()
                iosArm64()
                iosSimulatorArm64()
                @Suppress("UnstableApiUsage")
                androidLibrary {
                    namespace = defaultNamespace()
                    compileSdk = libs.findVersion("android-compileSdk").get().requiredVersion.toInt()
                    minSdk = libs.findVersion("android-minSdk").get().requiredVersion.toInt()
                    compilerOptions {
                        jvmTarget.set(JvmTarget.JVM_17)
                    }
                }
            }
        }
    }
}