import com.teamoffroad.convention.configureHiltAndroid
import com.teamoffroad.convention.configureKotlinAndroid
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply

class AndroidApplicationConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            apply(plugin = "com.android.application")

            configureKotlinAndroid()
            configureHiltAndroid()
            // TODO: Add more test configurations
        }
    }
}
