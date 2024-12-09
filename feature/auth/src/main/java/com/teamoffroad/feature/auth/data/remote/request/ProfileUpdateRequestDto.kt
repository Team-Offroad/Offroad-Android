package com.teamoffroad.feature.auth.data.remote.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ProfileUpdateRequestDto(
    @SerialName("nickname")
    val nickname: String,
    @SerialName("year")
    val year: Int?,
    @SerialName("month")
    val month: Int?,
    @SerialName("day")
    val day: Int?,
    @SerialName("gender")
    val gender: String?,
    @SerialName("characterId")
    val characterId: Int
)
