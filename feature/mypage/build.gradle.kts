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
    implementation(libs.coil.svg)
    implementation(libs.lottie.compose)
    implementation(libs.gson)
    implementation(libs.google.play.services.auth)
    implementation(libs.kotlinx.immutable)
    implementation(project(":feature:auth"))
}
