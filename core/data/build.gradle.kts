import com.teamoffroad.app.setNamespace

plugins {
    id("offroad.android.library")
    id("offroad.android.hilt")
    alias(libs.plugins.jetbrains.kotlin.serialization)
}

android {
    setNamespace("core.data")
}

dependencies {

    implementation(project(":core:model"))
    implementation(project(":core:datastore"))

    implementation(libs.retrofit)
    implementation(libs.retrofit2.kotlinx.serialization)
    implementation(libs.okhttp.logging.interceptor)
    implementation(libs.kotlinx.serialization)
}
