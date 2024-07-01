plugins {
    `kotlin-dsl`
    `kotlin-dsl-precompiled-script-plugins`
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

dependencies {
    compileOnly(libs.android.gradle.plugin)
    compileOnly(libs.kotlin.gradle.plugin)
    compileOnly(libs.ksp.gradle.plugin)
    compileOnly(libs.compose.compiler.gradle.plugin)
}

gradlePlugin {
    plugins {
        register("androidApplication") {
            id = "offroad.android.application"
            implementationClass = "AndroidApplicationConventionPlugin"
        }
        register("androidCompose") {
            id = "offroad.android.compose"
            implementationClass = "AndroidComposeConventionPlugin"
        }
        register("androidLibrary") {
            id = "offroad.android.library"
            implementationClass = "AndroidLibraryConventionPlugin"
        }
        register("androidFeature") {
            id = "offroad.android.feature"
            implementationClass = "AndroidFeatureConventionPlugin"
        }
        register("kotlinLibrary") {
            id = "offroad.kotlin.library"
            implementationClass = "KotlinLibraryConventionPlugin"
        }
        register("androidHilt") {
            id = "offroad.android.hilt"
            implementationClass = "com.teamoffroad.app.HiltAndroidPlugin"
        }
        register("kotlinHilt") {
            id = "offroad.kotlin.hilt"
            implementationClass = "com.teamoffroad.app.HiltKotlinPlugin"
        }
    }
}
