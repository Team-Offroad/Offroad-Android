import com.teamoffroad.app.setNamespace

plugins {
    id("offroad.android.library")
}

android {
    setNamespace("core.datastore")
}

dependencies {
    api(project(":core:model"))
    implementation(project(":core:common"))
    implementation(libs.androidx.datastore)
    testImplementation(libs.junit)
}
