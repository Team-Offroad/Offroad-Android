import com.teamoffroad.app.getApiKey
import com.teamoffroad.app.setNamespace

plugins {
    id("offroad.android.feature")
    id("offroad.android.library")
    id("offroad.android.compose")
    alias(libs.plugins.jetbrains.kotlin.serialization)
}

android {
    setNamespace("feature.auth")

    defaultConfig {
        buildConfigField("String", "GOOGLE_CLIENT_ID", getApiKey("google.client.id"))
    }

    buildFeatures {
        buildConfig = true
        compose = true
    }
}

dependencies {
    implementation(project(":core:designsystem"))

    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.datastore)
    implementation(libs.androidx.constraintlayout.compose)
    implementation(libs.google.play.services.auth)
    implementation(libs.retrofit.kotlinx.serialization)
    implementation(libs.coil.svg)
}
