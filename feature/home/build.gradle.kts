import com.teamoffroad.app.setNamespace

plugins {
    id("offroad.android.feature")
    alias(libs.plugins.jetbrains.kotlin.serialization)
}

android {
    setNamespace("feature.home")
}

dependencies {
    implementation(project(":feature:auth"))
    implementation(project(":feature:characterchat"))
    implementation(libs.retrofit.kotlinx.serialization)
    implementation(libs.androidx.appcompat)
    implementation(libs.google.accompanist.permissions)
    implementation(libs.gson)
    implementation(libs.lottie.compose)
    implementation(libs.coil.svg)
}
