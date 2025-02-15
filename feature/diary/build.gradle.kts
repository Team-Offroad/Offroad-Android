import com.teamoffroad.app.setNamespace

plugins {
    id("offroad.android.feature")
    id("offroad.android.library")
    id("offroad.android.compose")
    alias(libs.plugins.jetbrains.kotlin.serialization)
}

android {
    setNamespace("feature.diary")

    buildFeatures {
        buildConfig = true
        compose = true
    }
}

dependencies {
    implementation(libs.androidx.appcompat)
}