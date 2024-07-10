import com.teamoffroad.app.configureHiltAndroid
import com.teamoffroad.app.configureKotestAndroid
import com.teamoffroad.app.configureKotlinAndroid
import com.teamoffroad.app.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.dependencies

class AndroidApplicationConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            apply(plugin = "com.android.application")

            configureKotlinAndroid()
            configureHiltAndroid()
            configureKotestAndroid()
        }
    }
}
