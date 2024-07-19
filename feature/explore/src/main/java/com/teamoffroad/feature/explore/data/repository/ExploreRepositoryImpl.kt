package com.teamoffroad.feature.explore.data.repository

import com.teamoffroad.feature.explore.data.mapper.toDomain
import com.teamoffroad.feature.explore.data.remote.request.ExploreLocationAuthRequestDto
import com.teamoffroad.feature.explore.data.remote.request.ExploreQrAuthRequestDto
import com.teamoffroad.feature.explore.data.remote.service.ExploreService
import com.teamoffroad.feature.explore.domain.model.ExploreLocationResult
import com.teamoffroad.feature.explore.domain.model.ExploreQrResult
import com.teamoffroad.feature.explore.domain.model.Place
import com.teamoffroad.feature.explore.domain.repository.ExploreRepository
import javax.inject.Inject

class ExploreRepositoryImpl @Inject constructor(
    private val exploreService: ExploreService,
) : ExploreRepository {

    override suspend fun getPlaces(latitude: Double, longitude: Double): List<Place> {
        return exploreService.getPlaces(latitude, longitude).data?.places?.map { it.toDomain() } ?: emptyList()
    }

    override suspend fun postExploreAuth(placeId: Long, qrCode: String, latitude: Double, longitude: Double): ExploreQrResult {
        return exploreService.authenticateQr(ExploreQrAuthRequestDto(placeId, qrCode, latitude, longitude)).data?.toDomain() ?: ExploreQrResult(
            false,
            ""
        )
    }

    override suspend fun postLocationAuth(placeId: Long, latitude: Double, longitude: Double): ExploreLocationResult {
        return exploreService.authenticateLocation(ExploreLocationAuthRequestDto(placeId, latitude, longitude)).data?.toDomain()
            ?: ExploreLocationResult(
                false,
                ""
            )
    }
}
