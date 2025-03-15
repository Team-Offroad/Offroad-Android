import com.teamoffroad.app.setNamespace

plugins {
    id("offroad.android.feature")
    alias(libs.plugins.jetbrains.kotlin.serialization)
}

android {
    setNamespace("feature.diary")
}

dependencies {
    implementation(libs.androidx.appcompat)
    implementation(libs.kotlinx.immutable)
    implementation(libs.retrofit.kotlinx.serialization)
}