package com.teamoffroad.feature.explore.presentation.model

import com.teamoffroad.feature.explore.domain.model.Location

data class CourseQuestPlaceUiModel(
    val category: PlaceCategory,
    val name: String,
    val address: String,
    val position: Location,
    val isVisited: Boolean,
    val categoryImage: String,
    val description: String,
    val placeId: Long,
)
