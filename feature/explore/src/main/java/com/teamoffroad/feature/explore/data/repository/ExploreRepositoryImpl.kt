package com.teamoffroad.feature.explore.data.repository

import com.teamoffroad.feature.explore.data.mapper.toDomain
import com.teamoffroad.feature.explore.data.remote.request.ExploreAuthRequestDto
import com.teamoffroad.feature.explore.data.remote.service.ExploreService
import com.teamoffroad.feature.explore.domain.model.ExploreResult
import com.teamoffroad.feature.explore.domain.model.Place
import com.teamoffroad.feature.explore.domain.repository.ExploreRepository
import javax.inject.Inject

class ExploreRepositoryImpl @Inject constructor(
    private val exploreService: ExploreService,
) : ExploreRepository {

    override suspend fun getPlaces(latitude: Double, longitude: Double): List<Place> {
        return exploreService.getPlaces(latitude, longitude).data?.places?.map { it.toDomain() } ?: emptyList()
    }

    override suspend fun postExploreAuth(placeId: Long, qrCode: String, latitude: Double, longitude: Double): ExploreResult {
        return exploreService.authenticateUser(ExploreAuthRequestDto(placeId, qrCode, latitude, longitude)).data?.toDomain() ?: ExploreResult(
            false,
            ""
        )
    }
}
