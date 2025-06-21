import com.teamoffroad.app.setNamespace

plugins {
    id("offroad.android.feature")
    alias(libs.plugins.jetbrains.kotlin.serialization)
}

android {
    setNamespace("feature.recommendplace")
}

dependencies {
    implementation(project(":feature:explore"))
    implementation(project(":feature:characterchat"))
    implementation(libs.androidx.appcompat)
    implementation(libs.kotlinx.immutable)
    implementation(libs.retrofit.kotlinx.serialization)
    implementation(libs.lottie.compose)
    implementation(libs.naver.map.compose)
    implementation(libs.naver.map.location)
    implementation(libs.google.play.services.location)
    implementation(libs.bundles.offroad.map)
}