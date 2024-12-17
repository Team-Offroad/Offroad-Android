package com.teamoffroad.feature.explore.domain.repository

import com.teamoffroad.feature.explore.domain.model.Place

interface PlaceRepository {

    suspend fun fetchMapPlaces(latitude: Double, longitude: Double, limit: Int): List<Place>
}
