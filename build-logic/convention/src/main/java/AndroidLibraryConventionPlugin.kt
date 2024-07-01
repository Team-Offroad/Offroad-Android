import com.teamoffroad.convention.configureCoroutineAndroid
import com.teamoffroad.convention.configureHiltAndroid
import com.teamoffroad.convention.configureKotlinAndroid
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply

class AndroidLibraryConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            apply(plugin = "com.android.library")

            // TODO: Add more test dependencies
            configureKotlinAndroid()
            configureCoroutineAndroid()
            configureHiltAndroid()
        }
    }
}
