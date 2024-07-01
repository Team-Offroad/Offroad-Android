import com.teamoffroad.app.setNamespace

plugins {
    id("offroad.android.library")
}

android {
    setNamespace("core.datastore")
}

dependencies {
    testImplementation(libs.junit)
    implementation(libs.androidx.datastore)
}
