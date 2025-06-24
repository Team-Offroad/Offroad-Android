package com.teamoffroad.feature.explore.presentation.model

import com.teamoffroad.feature.explore.domain.model.Location

data class CourseQuestPlacesUiModel(
    val places: List<CourseQuestPlaceUiModel> = emptyList(),
) {
    val centerLocation: Location
        get() =
            Location(
                latitude = places.map { it.position.latitude }.average(),
                longitude = places.map { it.position.longitude }.average(),
            )

    val isComplete: Boolean
        get() = places.all { it.isVisited }
}
