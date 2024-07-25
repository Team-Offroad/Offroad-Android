import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties
import com.teamoffroad.app.setNamespace

plugins {
    id("offroad.android.library")
    id("offroad.android.hilt")
    id("offroad.android.compose")
    alias(libs.plugins.jetbrains.kotlin.serialization)
}

android {
    setNamespace("core.common")

    defaultConfig {
        buildConfigField("String", "BASE_URL", gradleLocalProperties(rootDir, providers).getProperty("base.url"))
    }

    buildFeatures {
        buildConfig = true
    }
}

dependencies {
    implementation(libs.retrofit)
    implementation(libs.retrofit.kotlinx.serialization)
    implementation(libs.kotlinx.serialization)
    implementation(libs.okhttp.logging.interceptor)
    implementation(libs.androidx.datastore)
    implementation(libs.androidx.activity.compose)
}
