package com.teamoffroad.feature.explore.domain.model

data class CourseQuestPlace(
    val category: String,
    val name: String,
    val address: String,
    val position: Location,
    val isVisited: Boolean,
    val categoryImage: String,
    val description: String,
    val placeId: Long,
)
