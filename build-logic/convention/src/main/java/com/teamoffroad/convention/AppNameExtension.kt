package com.teamoffroad.convention

import org.gradle.api.Project

fun Project.setNamespace(name: String) {
    androidExtension.apply {
        namespace = "com.teamoffroad.offroad.$name"
    }
}
