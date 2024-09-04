import com.teamoffroad.app.setNamespace

plugins {
    id("offroad.android.feature")
    alias(libs.plugins.jetbrains.kotlin.serialization)
}

android {
    setNamespace("feature.mypage")
}

dependencies {
    implementation(libs.retrofit.kotlinx.serialization)
    implementation(libs.coil.svg)
    implementation(libs.lottie.compose)
    implementation(libs.kotlinx.collections.immutable)
}