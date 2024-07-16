package com.teamoffroad.feature.explore.domain.repository

import com.teamoffroad.feature.explore.domain.model.Place

interface ExploreRepository {

    suspend fun getPlaces(latitude: Double, longitude: Double): List<Place>
}
