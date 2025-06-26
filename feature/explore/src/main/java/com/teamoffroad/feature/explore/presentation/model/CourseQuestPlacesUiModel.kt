package com.teamoffroad.feature.explore.presentation.model

import com.teamoffroad.feature.explore.domain.model.Location
import com.teamoffroad.feature.explore.domain.model.Location.Companion.toLatLng

data class CourseQuestPlacesUiModel(
    val places: List<CourseQuestPlaceUiModel> = emptyList(),
) {
    val centerLocation: Location
        get() {
            val averageLatitude = places.map { it.position.latitude }.average()
            val averageLongitude = places.map { it.position.longitude }.average()

            val averageLocation = Location(latitude = averageLatitude, longitude = averageLongitude).toLatLng()

            val closestPlace =
                places.minByOrNull { place ->
                    place.position.toLatLng().distanceTo(averageLocation)
                }

            return closestPlace?.position ?: Location()
        }

    val isComplete: Boolean
        get() = places.all { it.isVisited }

    val leftCount: Int
        get() = places.count { !it.isVisited }
}
