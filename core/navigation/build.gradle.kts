import com.teamoffroad.app.setNamespace

plugins {
    id("offroad.android.library")
    id("offroad.android.compose")
    alias(libs.plugins.kotlin.serialization)

}

android {
    setNamespace("core.navigation")
}

dependencies {
    implementation(libs.kotlinx.serialization.json)
}
