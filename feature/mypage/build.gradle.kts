import com.teamoffroad.app.setNamespace

plugins {
    id("offroad.android.feature")
    alias(libs.plugins.jetbrains.kotlin.serialization)
}

android {
    setNamespace("feature.mypage")
}

dependencies {
    implementation(project(":feature:auth"))
    implementation(libs.retrofit.kotlinx.serialization)
    implementation(libs.lottie.compose)
    implementation(libs.gson)
    implementation(libs.kotlinx.immutable)
    implementation(libs.process.phoenix)
}
