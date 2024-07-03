import com.teamoffroad.app.configureHiltKotlin
import org.gradle.api.Plugin
import org.gradle.api.Project

class KotlinHiltConventionPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            configureHiltKotlin()
        }
    }
}
