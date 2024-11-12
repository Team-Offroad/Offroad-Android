import com.teamoffroad.app.setNamespace

plugins {
    id("offroad.android.feature")
    alias(libs.plugins.jetbrains.kotlin.serialization)
}

android {
    setNamespace("feature.characterchat")
}

dependencies {
    implementation(libs.retrofit.kotlinx.serialization)
}
