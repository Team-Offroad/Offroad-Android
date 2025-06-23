package com.teamoffroad.feature.explore.domain.model

data class CourseQuestPlace(
    val category: String,
    val name: String,
    val address: String,
    val latitude: Double,
    val longitude: Double,
    val isVisited: Boolean,
    val categoryImage: String,
    val description: String,
)
