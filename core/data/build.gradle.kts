import com.teamoffroad.app.setNamespace

plugins {
    id("offroad.android.library")
    alias(libs.plugins.jetbrains.kotlin.serialization)
}

android {
    setNamespace("core.data")
}

dependencies {
    implementation(project(":core:datastore"))

    implementation(libs.retrofit.kotlinx.serialization)
}