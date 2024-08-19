package com.teamoffroad.feature.explore.data.repository

import com.teamoffroad.feature.explore.data.mapper.toDomain
import com.teamoffroad.feature.explore.data.remote.service.PlaceService
import com.teamoffroad.feature.explore.domain.model.Place
import com.teamoffroad.feature.explore.domain.repository.PlaceRepository
import javax.inject.Inject

class PlaceRepositoryImpl @Inject constructor(
    private val placeService: PlaceService,
) : PlaceRepository {

    override suspend fun fetchPlaces(latitude: Double, longitude: Double): List<Place> {
        return placeService.getPlaces(latitude, longitude).data?.places?.map { it.toDomain() } ?: emptyList()
    }
}
