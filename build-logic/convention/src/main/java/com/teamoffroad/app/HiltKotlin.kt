package com.teamoffroad.app

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

internal fun Project.configureHiltKotlin() {
    with(pluginManager) {
        apply("com.google.devtools.ksp")
    }

    dependencies {
        "implementation"(libs.findLibrary("hilt.core").get())
        "ksp"(libs.findLibrary("hilt.compiler").get())
    }
}
