package com.teamoffroad.feature.auth.domain.model

data class UserToken(
    val accessToken: String,
    val refreshToken: String,
)
