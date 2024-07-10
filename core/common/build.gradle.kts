import com.teamoffroad.app.setNamespace

plugins {
    id("offroad.android.library")
    id("offroad.android.hilt")
    id("offroad.android.compose")
    alias(libs.plugins.jetbrains.kotlin.serialization)
}

android {
    setNamespace("core.common")

    buildFeatures {
        compose = true
    }
}

dependencies {
    implementation(project(":core:designsystem"))
    implementation(libs.androidx.appcompat)

    implementation(libs.retrofit)
    implementation(libs.retrofit.kotlinx.serialization)
    implementation(libs.kotlinx.serialization)
    implementation(libs.okhttp.logging.interceptor)
}
