import com.teamoffroad.app.setNamespace

plugins {
    id("offroad.android.application")
}

android {
    setNamespace("app")

    defaultConfig {
        applicationId = "com.teamoffroad.app"
        versionCode = libs.versions.versionCode.get().toInt()
        versionName = libs.versions.versionName.get()
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }

    buildTypes {
        getByName("release") {
            signingConfig = signingConfigs.getByName("debug")
        }

        create("benchmark") {
            matchingFallbacks.add("release")
            signingConfig = signingConfigs.getByName("debug")
            isDebuggable = false
        }
    }
}

dependencies {
    implementation(project(":core:navigation"))
    implementation(project(":core:designsystem"))
    implementation(project(":feature:main"))
    implementation(project(":feature:home"))
}
