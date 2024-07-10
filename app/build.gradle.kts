import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties
import com.teamoffroad.app.setNamespace

plugins {
    id("offroad.android.application")
}

android {
    setNamespace("app")

    defaultConfig {
        applicationId = "com.teamoffroad.offroad.app"
        versionCode = libs.versions.versionCode.get().toInt()
        versionName = libs.versions.versionName.get()

        manifestPlaceholders["NAVER_CLIENT_ID"] = gradleLocalProperties(rootDir, providers).getProperty("naver.client.id")
    }

    buildFeatures {
        buildConfig = true
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
}

dependencies {
    implementation(project(":core:common"))
    implementation(project(":core:navigation"))
    implementation(project(":core:designsystem"))
    implementation(project(":feature:main"))
    implementation(project(":feature:auth"))
    implementation(project(":feature:home"))
    implementation(project(":feature:explore"))
    implementation(project(":feature:mypage"))

    implementation(libs.naver.map.sdk)
}
