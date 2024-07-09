package com.teamoffroad.app

import org.gradle.api.Project
import org.jetbrains.kotlin.konan.properties.Properties

fun Project.getApiKey(propertyKey: String): String {
    val properties = Properties()
    properties.load(rootProject.file("local.properties").inputStream())
    return properties.getProperty(propertyKey) ?: throw IllegalArgumentException("Invalid Key")
}
