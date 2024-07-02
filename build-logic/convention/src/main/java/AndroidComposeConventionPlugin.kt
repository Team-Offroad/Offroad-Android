import com.teamoffroad.app.configureComposeAndroid
import org.gradle.api.Plugin
import org.gradle.api.Project

class AndroidComposeConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        target.configureComposeAndroid()
    }
}
