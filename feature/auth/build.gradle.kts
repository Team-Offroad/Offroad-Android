import com.teamoffroad.app.setNamespace

plugins {
    id("offroad.android.feature")
    id("offroad.android.library")
}

android {
    setNamespace("feature.home")
}

dependencies {
    implementation(libs.androidx.datastore)
}
