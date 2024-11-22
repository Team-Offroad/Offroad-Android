import com.teamoffroad.app.setNamespace

plugins {
    id("offroad.android.library")
    id("offroad.android.compose")
}

android {
    setNamespace("core.designsystem")
}

dependencies {
    implementation(libs.androidx.appcompat)
    implementation(libs.lottie.compose)
    implementation(libs.bundles.coil)
}
