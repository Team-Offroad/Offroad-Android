plugins {
    id("offroad.android.library")
    id("offroad.android.hilt")
    // TODO: Add room plugin
}

android {
    namespace = "com.teamoffroad.core.database"
}

dependencies {
    api(project(":core:model"))
    implementation(project(":core:common"))
}
