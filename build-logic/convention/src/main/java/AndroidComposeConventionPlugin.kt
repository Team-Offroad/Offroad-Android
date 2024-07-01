import com.teamoffroad.convention.configureComposeAndroid
import org.gradle.api.Project

class AndroidComposeConventionPlugin {
    fun apply(target: Project) {
        target.configureComposeAndroid()
    }
}
