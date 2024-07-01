import com.android.build.gradle.LibraryExtension
import com.teamoffroad.convention.configureHiltAndroid
import com.teamoffroad.convention.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies

class AndroidFeatureConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply {
                apply("offroad.android.library")
                apply("offroad.android.compose")
            }
            extensions.configure<LibraryExtension> {
                packaging {
                    resources {
                        excludes.add("META-INF/**")
                    }
                }
                defaultConfig {
                    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
                }
            }

            configureHiltAndroid()
            // TODO: Add more test configurations

            dependencies {
                "implementation"(project(":core"))

                val libs = extensions.libs
                "implementation"(libs.findLibrary("hilt.navigation.compose").get())
                "implementation"(libs.findLibrary("androidx.compose.navigation").get())
                "implementation"(libs.findLibrary("androidx.lifecycle.viewModelCompose").get())
                "implementation"(libs.findLibrary("androidx.lifecycle.runtimeCompose").get())
                // TODO: Add more test dependencies
            }
        }
    }
}
