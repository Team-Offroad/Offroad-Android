import com.teamoffroad.app.getApiKey
import com.teamoffroad.app.setNamespace

plugins {
    id("offroad.android.feature")
    id("offroad.android.library")
}

android {
    setNamespace("feature.auth")

    defaultConfig {
        buildConfigField("String", "GOOGLE_CLIENT_ID", getApiKey("google.client.id"))
    }

    buildFeatures {
        buildConfig = true
    }
}

dependencies {
    implementation(libs.androidx.datastore)
    implementation(libs.androidx.constraintlayout.compose)
}
