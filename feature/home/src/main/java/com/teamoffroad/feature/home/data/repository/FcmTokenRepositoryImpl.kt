package com.teamoffroad.feature.home.data.repository

import com.teamoffroad.feature.home.data.remote.request.FcmTokenRequestDto
import com.teamoffroad.feature.home.data.remote.service.FcmTokenService
import com.teamoffroad.feature.home.domain.repository.FcmTokenRepository
import javax.inject.Inject

class FcmTokenRepositoryImpl @Inject constructor(
    private val fcmTokenService: FcmTokenService
) : FcmTokenRepository {
    override suspend fun postFcmToken(fcmToken: String) {
        fcmTokenService.postFcmToken(FcmTokenRequestDto(fcmToken))
    }
}