package com.teamoffroad.feature.explore.presentation.model

import com.teamoffroad.feature.explore.domain.model.MapPosition

data class CourseQuestPlaceUiModel(
    val category: PlaceCategory,
    val name: String,
    val address: String,
    val position: MapPosition,
    val isVisited: Boolean,
    val categoryImage: String,
    val description: String,
)
