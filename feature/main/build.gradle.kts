import com.teamoffroad.app.setNamespace

plugins {
    id("offroad.android.feature")
}

android {
    setNamespace("feature.main")

    defaultConfig {
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
}

dependencies {
    implementation(project(":feature:auth"))
    implementation(project(":feature:home"))
    implementation(project(":feature:explore"))
    implementation(project(":feature:mypage"))
    implementation(project(":feature:characterchat"))

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.lifecycle.runtime.compose)
    implementation(libs.androidx.lifecycle.viewmodel.compose)
    implementation(libs.kotlinx.immutable)
    implementation(libs.eventbus)
    implementation(libs.androidx.constraintlayout.compose)
    implementation(libs.accompanist.insets)
}
