package com.teamoffroad.feature.explore.data.repository

import com.teamoffroad.feature.explore.data.mapper.toDomain
import com.teamoffroad.feature.explore.data.remote.request.ExploreLocationAuthRequestDto
import com.teamoffroad.feature.explore.data.remote.service.UserService
import com.teamoffroad.feature.explore.domain.model.ExploreLocationResult
import com.teamoffroad.feature.explore.domain.repository.UserRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userService: UserService,
) : UserRepository {

    override suspend fun saveLocationAuth(placeId: Long, latitude: Double, longitude: Double): ExploreLocationResult {
        return userService.postLocationAuth(ExploreLocationAuthRequestDto(placeId, latitude, longitude)).data?.toDomain()
            ?: ExploreLocationResult(
                false,
                "",
                emptyList(),
            )
    }
}
