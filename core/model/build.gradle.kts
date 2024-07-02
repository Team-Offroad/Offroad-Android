plugins {
    id("offroad.kotlin.library")
    alias(libs.plugins.jetbrains.kotlin.serialization)
}

dependencies {
    implementation(libs.kotlinx.serialization)
}
