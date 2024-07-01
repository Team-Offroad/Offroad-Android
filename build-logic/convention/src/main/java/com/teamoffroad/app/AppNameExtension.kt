package com.teamoffroad.app

import org.gradle.api.Project

fun Project.setNamespace(name: String) {
    androidExtension.apply {
        namespace = "com.teamoffroad.app.$name"
    }
}
