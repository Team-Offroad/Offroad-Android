package com.teamoffroad.feature.home.domain.repository

interface FcmTokenRepository {
    suspend fun postFcmToken(fcmToken: String)
}