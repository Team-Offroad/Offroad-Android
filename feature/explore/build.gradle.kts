import com.teamoffroad.app.setNamespace

plugins {
    id("offroad.android.feature")
}

android {
    setNamespace("feature.explore")
}

dependencies {
    implementation(libs.naver.map.compose)
    implementation(libs.naver.map.location)
    implementation(libs.google.play.services.location)

    implementation(libs.bundles.offroad.map)
}
