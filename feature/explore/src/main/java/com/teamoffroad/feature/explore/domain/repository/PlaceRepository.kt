package com.teamoffroad.feature.explore.domain.repository

import com.teamoffroad.feature.explore.domain.model.Place

interface PlaceRepository {

    suspend fun fetchMapPlaces(latitude: Double, longitude: Double, limit: Int): List<Place>

    suspend fun fetchPlaces(latitude: Double, longitude: Double, limit: Int, cursorDistance: Double?): List<Place>
}
