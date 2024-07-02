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
    api(project(":core:common"))
    api(project(":core:database"))
    api(project(":core:datastore"))
    api(project(":core:network"))
}