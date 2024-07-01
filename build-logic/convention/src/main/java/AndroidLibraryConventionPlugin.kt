import com.teamoffroad.app.configureCoroutineAndroid
import com.teamoffroad.app.configureHiltAndroid
import com.teamoffroad.app.configureKotlinAndroid
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
