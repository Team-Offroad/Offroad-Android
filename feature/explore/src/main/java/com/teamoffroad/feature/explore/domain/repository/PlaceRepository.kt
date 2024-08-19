package com.teamoffroad.feature.explore.domain.repository

import com.teamoffroad.feature.explore.domain.model.Place

interface PlaceRepository {

    suspend fun fetchPlaces(latitude: Double, longitude: Double): List<Place>
}
