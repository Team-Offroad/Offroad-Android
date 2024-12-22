package com.teamoffroad.feature.auth.domain.model

data class UserProfile(
    val nickname: String,
    val year: Int?,
    val month: Int?,
    val day: Int?,
    val gender: String?,
    val characterId: Int,
)
