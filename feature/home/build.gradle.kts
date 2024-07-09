import com.teamoffroad.app.setNamespace

plugins {
    id("offroad.android.feature")
    alias(libs.plugins.jetbrains.kotlin.serialization)
}

android {
    setNamespace("feature.home")

    buildFeatures {
        compose = true
    }
}

dependencies {
    implementation(libs.retrofit.kotlinx.serialization)
    implementation(libs.androidx.constraintlayout.compose)
}
