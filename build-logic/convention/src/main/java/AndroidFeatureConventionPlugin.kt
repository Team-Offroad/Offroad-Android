import com.android.build.gradle.LibraryExtension
import com.teamoffroad.app.configureHiltAndroid
import com.teamoffroad.app.libs
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
                "implementation"(project(":core:common"))
                "implementation"(project(":core:designsystem"))
                "implementation"(project(":core:navigation"))

                // TODO: Add more test dependencies

                "implementation"(libs.findLibrary("hilt.navigation.compose").get())
                "implementation"(libs.findLibrary("navigation.compose").get())
                "implementation"(libs.findLibrary("androidx.lifecycle.runtime.compose").get())
                "implementation"(libs.findLibrary("androidx.lifecycle.viewmodel.compose").get())
                "implementation"(libs.findLibrary("coil").get())
                "implementation"(libs.findLibrary("coil.svg").get())
                "androidTestImplementation"(libs.findLibrary("navigation.test").get())
            }
        }
    }
}
