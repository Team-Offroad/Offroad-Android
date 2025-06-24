package com.teamoffroad.feature.explore.presentation.model

import com.teamoffroad.feature.explore.domain.model.MapPosition

data class CourseQuestPlacesUiModel(
    val places: List<CourseQuestPlaceUiModel> = emptyList(),
) {
    val centerMapPosition: MapPosition
        get() =
            MapPosition(
                latitude = places.map { it.position.latitude }.average(),
                longitude = places.map { it.position.longitude }.average(),
            )

    val isComplete: Boolean
        get() = places.all { it.isVisited }
}
