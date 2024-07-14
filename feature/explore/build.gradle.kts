import com.teamoffroad.app.setNamespace

plugins {
    id("offroad.android.feature")
    alias(libs.plugins.jetbrains.kotlin.serialization)
}

android {
    setNamespace("feature.explore")
}

dependencies {
    implementation(libs.naver.map.compose)
    implementation(libs.naver.map.location)
    implementation(libs.google.play.services.location)
    implementation(libs.retrofit.kotlinx.serialization)
    implementation(libs.bundles.offroad.map)
    
    implementation(libs.coil)
}
