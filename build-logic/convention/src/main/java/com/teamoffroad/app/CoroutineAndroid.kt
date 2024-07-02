package com.teamoffroad.app

import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

internal fun Project.configureCoroutineAndroid() {
    configureCoroutineKotlin()
    dependencies {
        "implementation"(libs.findLibrary("kotlin.coroutines.android").get())
    }
}

internal fun Project.configureCoroutineKotlin() {
    dependencies {
        "implementation"(libs.findLibrary("kotlin.coroutines.core").get())
    }
}
