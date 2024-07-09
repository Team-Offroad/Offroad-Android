import com.teamoffroad.app.setNamespace

plugins {
    id("offroad.android.library")
}

android {
    setNamespace("core.datastore")
}

dependencies {
    implementation(libs.androidx.datastore)
}